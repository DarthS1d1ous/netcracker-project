import {Component, Input, OnInit} from "@angular/core";
import {Post} from "../../../../models/post/post";
import {PostService} from "../../../../services/post.service";
import {Observable, Subscription} from "rxjs";
import {tap} from "rxjs/operators";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: "app-main-post",
  templateUrl: "./main-post.component.html"
})
export class MainPostComponent implements OnInit {

  @Input()
  post: Post;
  date: Date;
  comment: string ='';
  imageSrc: string;
  userId = JSON.parse(localStorage.getItem("user")).id;
  postLikedByUser: boolean = false;
  postTags: string = '';
  mainPost: boolean = false;
  singlePost: boolean = false;
  private subscriptions: Subscription[] = [];

  format: string = 'd MMMM, h:mm:ss';

  constructor(private postService: PostService, private router: ActivatedRoute) {
  }

  ngOnInit() {
    if(this.post == null){
      this.subscriptions.push(this.router.params.subscribe((params) => {
        this.loadPost(params.id);
        this.singlePost = true;
      }));
    } else {
      this.loadData();
      this.mainPost = true;
    }
  }

  checkMainPhoto(photo: string): string {
    if (photo === "") {
      return "../../../../../../assets/images/default-user-icon.png"
    } else return "data:image/png;base64," + photo;
  }

  putLike() {
    const like$: Observable<boolean> = this.postLikedByUser ?
      this.postService.deleteLike(this.post.id, this.userId).pipe(tap(() => this.postLikedByUser = false)) :
      this.postService.saveLike(this.post.id, this.userId).pipe(tap(() => this.postLikedByUser = true));
    like$.pipe(tap(() => this.recountLikes())).subscribe();
  }

  recountLikes() {
    this.postService.findPostById(this.post.id).subscribe(post => {
      this.post.userLikes = post.userLikes;
    });
  }

  addAllTagsToPage() {
    this.post.postTags.forEach(tag => this.postTags += '#' + tag.title);
  }

  private loadPost(id: number) {
    this.subscriptions.push(this.postService.findPostById(id).subscribe(post => {
      this.post = post;
     this.loadData();
    }));
  }

  private loadData(){
    this.date = new Date(this.post.timeCreation);
    this.addAllTagsToPage();
    this.postLikedByUser = !!this.post.userLikes.find(user => user.id == this.userId);
    this.post.comments = this.post.comments.sort((a, b) =>  this.compareComments(a,b));
  }

  compareComments( a, b ) {
    if ( a.id < b.id ){
      return 1;
    }
    if ( a.id > b.id ){
      return -1;
    }
    return 0;
  }

  addComment(comment: string){

  }

  ngOnDestroy(): void {
    this.subscriptions.forEach(subscription => subscription.unsubscribe());
  }
}
