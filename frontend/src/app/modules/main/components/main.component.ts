import {Component, OnDestroy, OnInit} from "@angular/core";
import {Post} from "../../../models/post";
import {Subscription} from "rxjs";
import {PostService} from "../../../services/post.service";
import {UserService} from "../../../services/user.service";
import {User} from "../../../models/user";
import {PagerComponent} from "ngx-bootstrap";
import {TagService} from "../../../services/tag.service";

@Component({
  selector: "app-main",
  templateUrl: "./main.component.html"
})
export class MainComponent implements OnInit, OnDestroy {

  config: any = {
    itemsPerPage: 7,
    currentPage: 1,
    totalItems: 0
  };
  inputTags: string = '';
  public postsBeforeSearching: Post[];
  public posts: Post[];
  private subscriptions: Subscription[] = [];

  constructor(private postService: PostService) {}

  ngOnInit() {
    this.loadPosts();
  }

  private loadPosts() {
    this.subscriptions.push(this.postService.findPosts(this.config.currentPage-1,this.config.itemsPerPage, "desc", "timeCreation").subscribe(page => {
      this.posts = page.content;
      this.postsBeforeSearching = page.content;
      this.config.totalItems= page.totalElements;
    }));
  }

  pageChanged(event){
    this.config.currentPage = event;
    this.loadPosts();
    window.scroll(0,0);
  }

  searchPostsByTags(tags: string){
    if(tags.trim().length!=0) {
      let titles = tags.split("#").filter(tag => tag != '').toString();
      this.postService.findPostsByTags(titles).subscribe(posts => {
        this.posts = posts;
        this.config.totalItems = posts.length;
      })
    } else {
      this.posts = this.postsBeforeSearching;
    }
  }

  ngOnDestroy(): void {
    this.subscriptions.forEach(subscription => subscription.unsubscribe());
  }

}
