import {Component, OnInit} from "@angular/core";
import {Post} from "../../../models/post/post";
import {Subscription} from "rxjs";
import {PostService} from "../../../services/post.service";
import {UserService} from "../../../services/user.service";
import {User} from "../../../models/user/user";
import {PagerComponent} from "ngx-bootstrap";
import {TagService} from "../../../services/tag.service";

@Component({
  selector: "app-main",
  templateUrl: "./main.component.html"
})
export class MainComponent implements OnInit {

  config: any = {
    itemsPerPage: 7,
    currentPage: 1,
    totalItems: 0
  };
  public posts: Post[];
  private subscriptions: Subscription[] = [];

  constructor(private postService: PostService, private userService: UserService) {}

  ngOnInit() {
    this.loadPosts();
    this.subscriptions
      .push(this.userService.getUserById(27).subscribe(user=>{
        localStorage.setItem("user", JSON.stringify(user));
        console.log(user)
      }))
  }

  private loadPosts() {
    this.subscriptions.push(this.postService.findPosts(this.config.currentPage-1,this.config.itemsPerPage, "desc", "timeCreation").subscribe(page => {
      this.posts = page.content;
      console.log(page.content);
      this.config.totalItems= page.totalElements;
      console.log(this.config)
    }));
  }

  pageChanged(event){
    this.config.currentPage = event;
    this.loadPosts();
    window.scroll(0,0);
  }

}
