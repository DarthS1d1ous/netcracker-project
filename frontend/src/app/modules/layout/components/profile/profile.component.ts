import {Component, OnInit} from "@angular/core";
import {StorageService} from "../../../../services/storage.service";
import {User} from "../../../../models/user";
import {Post} from "../../../../models/post";
import {PostService} from "../../../../services/post.service";
import {ActivatedRoute, Router} from "@angular/router";
import {Subscription} from "rxjs";
import {UserService} from "../../../../services/user.service";

@Component({
  selector: "app-profile",
  templateUrl: "./profile.component.html"
})
export class ProfileComponent implements OnInit {

  userProfile: User;
  currentUser: User = this.storageService.getCurrentUser();
  posts: Post[];
  userLikesCount: number;
  subscription: boolean = false;
  buttonSubscriptionTitle: string = 'Subscribe'
  private subscriptions: Subscription[] = [];

  constructor(private storageService: StorageService, private postService: PostService,
              private activateRouter: ActivatedRoute, private userService: UserService,
              private router: Router) {
  }

  ngOnInit() {
    console.log(this.currentUser)
    this.subscriptions.push(this.activateRouter.params.subscribe((params) => {
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
      this.userProfile = user;
      this.checkSubscription()
    })
  }

  checkSubscription() {
    this.subscription = false;
    this.buttonSubscriptionTitle = "Subscribe";
    this.userProfile.subscribers.forEach(value => {
      if (value.id == this.currentUser.id) {
        this.subscription = true;
        this.buttonSubscriptionTitle = "Unsubscribe";
      }
    })
  }

  deleteUser() {
    this.userService.deleteUser(this.userProfile.id).subscribe(user => {
      this.router.navigate(['/home']);
    })
  }

  subscribe() {
    console.log(this.subscription)
    console.log('current' +this.currentUser.id);
    console.log('profile'+ this.userProfile.id);
    if (this.subscription) {
      this.userService.deleteSubscription(this.currentUser.id, this.userProfile.id).subscribe(() => {
          this.userProfile.subscribers = this.userProfile.subscribers.filter(user => user.id != this.currentUser.id);
          this.checkSubscription();
        }
      );
    } else {
      this.userService.insertSubscription(this.currentUser.id, this.userProfile.id).subscribe(()=> {
        this.userProfile.subscribers.push(this.currentUser);
        this.checkSubscription()
      });
    }
  }
}
