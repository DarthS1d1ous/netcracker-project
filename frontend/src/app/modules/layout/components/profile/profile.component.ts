import {Component, Input, OnDestroy, OnInit} from "@angular/core";
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
              private activateRouter: ActivatedRoute, private userService:UserService,
              private router: Router) {
  }

  ngOnInit() {
    console.log(this.currentUser)
    this.subscriptions.push(this.activateRouter.params.subscribe((params) => {
      this.loadUser(params.id);
      this.setUserPosts(params.id);
      this.loadUserLikesCount(params.id);
    }));
    this.checkSubscription()
  }

  loadUserLikesCount(userId: number){
    this.userService.findUserLikesCount(userId).subscribe(count =>{
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

  loadUser(userId: number){
    this.userService.getUserById(userId).subscribe(user =>{
      this.userProfile = user;
      console.log(user)
    })
  }

  checkSubscription() {
    this.currentUser.subscriptions.forEach(value => {
      console.log(value.id);
      console.log(" userprofile" + this.userProfile.id);
      if (value.id == this.userProfile.id){
        this.subscription = true;
        this.buttonSubscriptionTitle = "Unsubscribe";
      }
    })
  }

  deleteUser(){
    this.userService.deleteUser(this.userProfile.id).subscribe( user => {
      this.router.navigate(['/home']);
    })
  }

  subscribe(){
    if(this.subscription){
      const subscride$ = this.currentUser.subscriptions.filter(user => user.id != this.userProfile.id);
      this.userService.saveUser(this.currentUser).subscribe(user => this.currentUser = user);
    } else {
      const subscride$ = this.currentUser.subscriptions.push(this.userProfile);
      this.userService.saveUser(this.currentUser).subscribe(user => this.currentUser = user);
    }
  }
}
