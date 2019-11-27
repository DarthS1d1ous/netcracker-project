import {Component, OnInit} from "@angular/core";
import {Post} from "../../../../models/post/post";
import {Subscription} from "rxjs";
import {PostService} from "../../../../services/post.service";

@Component({
  selector:"app-mini-posts",
  templateUrl:"./mini-posts.component.html"
})
export class MiniPostsComponent implements OnInit {

  public posts: Post[];
  private subscriptions: Subscription[] = [];

  constructor(private postService: PostService ) {}

  ngOnInit() {
    this.loadPosts()
  }

  private loadPosts() {
    this.subscriptions.push(this.postService.findPosts(0,4, "desc", "userLikes").subscribe(page => {
      this.posts = page.content;
      console.log(page.content);
    }));
  }

}
