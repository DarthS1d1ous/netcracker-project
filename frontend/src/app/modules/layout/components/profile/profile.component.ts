import {Component, OnInit} from "@angular/core";
import {StorageService} from "../../../../services/storage.service";
import {User} from "../../../../models/user";
import {Post} from "../../../../models/post";
import {PostService} from "../../../../services/post.service";
import {ActivatedRoute, Router} from "@angular/router";
import {Subscription} from "rxjs";
import {UserService} from "../../../../services/user.service";
import {ConfirmationDialog} from "../../../main/components/confirmation-dialog/confirmation-dialog.component";
import {MatDialog} from "@angular/material";

@Component({
  selector: "app-profile",
  templateUrl: "./profile.component.html"
})
export class ProfileComponent implements OnInit {

  userProfile: User;
  currentUser: User = this.storageService.getCurrentUser();
  posts: Post[];
  userLikesCount: number;
  buttonSubscriptionTitle: string;
  subscribers: User[];
  subscriptions: User[];
  usersWithSubscriptionButtons = new Map<User, string>();
  private sub: Subscription[] = [];
  operation: string;

  constructor(private storageService: StorageService, private postService: PostService,
              private activateRouter: ActivatedRoute, private userService: UserService,
              private router: Router, private dialog: MatDialog) {
  }

  ngOnInit() {
    console.log(this.currentUser)
    this.sub.push(this.activateRouter.params.subscribe((params) => {
      this.loadUser(params.id);
      this.setUserPosts(params.id);
      this.loadUserLikesCount(params.id);
    }));
  }

  loadUserLikesCount(userId: number) {
    this.userService.findUserLikesCount(userId).subscribe(count => {
      this.userLikesCount = count;
    })
  }

  checkMainPhoto(photo: string): string {
    if (photo == null) {
      return "../../../../../../assets/images/default-user-icon.png"
    } else return "data:image/png;base64," + photo;
  }

  setUserPosts(userId: number) {
    this.postService.findPostsByUserId(userId).subscribe(posts => {
      this.posts = posts;
    })
  }

  loadUser(userId: number) {
    this.userService.getUserById(userId).subscribe(user => {
      this.usersWithSubscriptionButtons.clear();
      this.userProfile = user;
      console.log(user);
      this.setSubscriptionButton(this.userProfile);
      this.subscriptions = user.subscriptions;
      this.subscribers = user.subscribers;
      this.subscribers.forEach(user => {
        this.usersWithSubscriptionButtons.set(user, "Subscribe");
      });
      this.subscribers.forEach(subscriber => {
        if (this.currentUser.subscriptions.find(user => user.id == subscriber.id)) {
          this.usersWithSubscriptionButtons.set(subscriber, "Unsubscribe");
        } else {
          this.usersWithSubscriptionButtons.set(subscriber, "Subscribe");
        }
      })
    })
  }

  reassignSubscriptionList(operation: number){
    this.usersWithSubscriptionButtons.clear();
    if(operation == 1) {
      this.operation = "Subscribers";
      this.subscribers.forEach(user => {
        this.usersWithSubscriptionButtons.set(user, "Subscribe");
      });
      this.subscribers.forEach(subscriber => {
        if (this.currentUser.subscriptions.find(user => user.id == subscriber.id)) {
          this.usersWithSubscriptionButtons.set(subscriber, "Unsubscribe");
        } else {
          this.usersWithSubscriptionButtons.set(subscriber, "Subscribe");
        }
      })
    } else {
      this.operation = "Subscriptions";
      this.subscriptions.forEach(user => {
        this.usersWithSubscriptionButtons.set(user, "Subscribe");
      });
      this.subscriptions.forEach(subscriber => {
        if (this.currentUser.subscriptions.find(user => user.id == subscriber.id)) {
          this.usersWithSubscriptionButtons.set(subscriber, "Unsubscribe");
        } else {
          this.usersWithSubscriptionButtons.set(subscriber, "Subscribe");
        }
      })
    }
  }

  checkSubscription(id: number): boolean {
    return this.currentUser.subscriptions.find(user => user.id == id) != null;
  }

  setSubscriptionButton(user: User) {
    if (user.id == this.userProfile.id) {
      this.buttonSubscriptionTitle = "Subscribe";
      this.usersWithSubscriptionButtons.delete(this.currentUser);
      if (this.checkSubscription(user.id)) {
        this.usersWithSubscriptionButtons.set(this.currentUser, "");
        this.buttonSubscriptionTitle = "Unsubscribe";
      }
    } else {
      if (this.checkSubscription(user.id)) {
        this.usersWithSubscriptionButtons.set(user, "Unsubscribe");
      } else {
        this.usersWithSubscriptionButtons.set(user, "Subscribe")
      }
    }
    console.log(this.usersWithSubscriptionButtons);
  }

  openDeleteUserDialog() {
    const dialogRef = this.openDialog();

    dialogRef.afterClosed().subscribe((confirmed: boolean) => {
      if (confirmed) {
        this.deleteUser();
      }
    });
  }

  openDialog(): any {
    return this.dialog.open(ConfirmationDialog, {
      data: {
        message: 'Are you sure want to delete?',
        buttonText: {
          ok: 'Yes',
          cancel: 'No'
        }
      }
    });
  }

  deleteUser() {
    this.userService.deleteUser(this.userProfile.id).subscribe(user => {
      this.router.navigate(['/home']);
    })
  }

  subscribe(user: User) {
    if (this.checkSubscription(user.id)) {
      this.userService.deleteSubscription(this.currentUser.id, user.id).subscribe(() => {
          if (user.id == this.userProfile.id) {
            this.userProfile.subscribers = this.userProfile.subscribers.filter(us => us.id != this.currentUser.id);
            this.subscribers = this.userProfile.subscribers;
          }
          if (this.currentUser.id == this.userProfile.id) {
            this.userProfile.subscriptions = this.userProfile.subscriptions.filter(us => us.id != user.id)
          }
          this.currentUser.subscriptions = this.currentUser.subscriptions.filter(current => current.id != user.id);
          this.storageService.setCurrentUser(this.currentUser);
          this.setSubscriptionButton(user);
        }
      );
    } else {
      this.userService.insertSubscription(this.currentUser.id, user.id).subscribe(() => {
        if (user.id == this.userProfile.id) {
          this.userProfile.subscribers.push(this.currentUser);
          this.subscribers = this.userProfile.subscribers;
        }
        if (this.currentUser.id == this.userProfile.id) {
          this.userProfile.subscriptions.push(user);
        }
        this.userService.getUserById(this.currentUser.id).subscribe(current => {
          this.currentUser = current;
          this.storageService.setCurrentUser(current);
          this.setSubscriptionButton(user);
        })
      });
    }
  }
}
