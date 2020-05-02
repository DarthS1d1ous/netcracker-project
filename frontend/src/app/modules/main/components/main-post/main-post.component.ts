import {Component, Input, OnDestroy, OnInit} from "@angular/core";
import {Post} from "../../../../models/post";
import {PostService} from "../../../../services/post.service";
import {Subscription} from "rxjs";
import {tap} from "rxjs/operators";
import {ActivatedRoute, Router} from "@angular/router";
import {User} from "../../../../models/user";
import {Comment} from "../../../../models/comment"
import {CommentService} from "../../../../services/comment.service";
import {StorageService} from "../../../../services/storage.service";

@Component({
  selector: "app-main-post",
  templateUrl: "./main-post.component.html"
})
export class MainPostComponent implements OnInit, OnDestroy {

  @Input()
  post: Post;
  date: Date;
  comment: string = '';
  imageSrc: string;
  user: User = this.storageService.getCurrentUser();
  postLikedByUser: boolean = false;
  postLikesCount: number;
  postTags: string = '';
  postComments: Comment[] = [];
  mainPost: boolean = false;
  singlePost: boolean = false;
  private subscriptions: Subscription[] = [];

  format: string = 'd MMMM, h:mm:ss';

  constructor(private postService: PostService, private router: ActivatedRoute,
              private commentService: CommentService, private storageService: StorageService,
              private navigateRouter: Router) {
  }

  ngOnInit() {
    if (this.post == null) {
      this.subscriptions.push(this.router.params.subscribe((params) => {
        this.loadPost(params.id);
        this.singlePost = true;
      }));
    } else {
      this.commentService.getCommentsByPostId(this.post.id).subscribe(comments => {
        this.postComments = comments;
      });
      this.loadData();
      this.mainPost = true;
    }
  }

  checkMainPhoto(photo: string): string {
    if (photo == null) {
      return "../../../../../../assets/images/default-user-icon.png"
    } else return "data:image/png;base64," + photo;
  }

  putLike() {
    if (!this.postLikedByUser) {
      this.post.userLikes.push(this.user);
      this.postService.savePost(this.post).pipe(tap(() => {
        this.postLikedByUser = true;
        this.postLikesCount++;
      })).subscribe()
    } else {
      this.post.userLikes = this.post.userLikes.filter(user => user.id != this.user.id);
      this.postService.savePost(this.post).pipe(tap(() => {
        this.postLikedByUser = false;
        this.postLikesCount--;
      })).subscribe();
    }
  }

  addAllTagsToPage() {
    this.post.postTags.forEach(tag => this.postTags += '#' + tag.title);
  }

  private loadPost(id: number) {
    this.subscriptions.push(this.postService.findPostById(id).subscribe(post => {
      this.post = post;
      this.loadData();
      this.commentService.getCommentsByPostId(this.post.id).subscribe(comments => {
        this.postComments = comments;
        console.log(comments)
        console.log(post.user.role)
        console.log(this.user.role)
      });
    }));
  }

  private loadData() {
    this.date = new Date(this.post.timeCreation);
    this.addAllTagsToPage();
    this.postLikesCount = this.post.userLikes.length;
    this.postLikedByUser = !!this.post.userLikes.find(user => user.id == this.user.id);
  }

  deletePost() {
    this.postService.deletePost(this.post.id).subscribe(() => {
      this.navigateRouter.navigate(['/home']);
    });
  }

  deleteComment(commentId: number) {
    this.commentService.deleteComment(commentId).subscribe(() => {
      this.postComments = this.postComments.filter(comment => comment.id!=commentId);
    });
  }

  addComment(commentMessage: string) {
    let comment = new Comment();
    comment.timeCreation = new Date();
    comment.message = commentMessage.trim();
    comment.post = this.post;
    comment.user = this.user;
    this.comment = '';
    this.commentService.saveComment(comment).subscribe(comment => {
      this.postComments.push(comment);
    });
  }

  resetTextArea(elem) {
    (<HTMLInputElement>document.getElementById(elem)).value = '';
  }

  ngOnDestroy(): void {
    this.subscriptions.forEach(subscription => subscription.unsubscribe());
  }
}
