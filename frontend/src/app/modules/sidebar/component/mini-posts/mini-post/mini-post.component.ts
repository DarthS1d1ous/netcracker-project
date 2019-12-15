import {Component, Input, OnInit} from "@angular/core";
import {Observable, Subscription} from "rxjs";
import {tap} from "rxjs/operators";
import {Post} from "../../../../../models/post";
import {PostService} from "../../../../../services/post.service";

@Component({
  selector: "app-mini-post",
  templateUrl: "./mini-post.component.html"
})
export class MiniPostComponent implements OnInit {

  @Input()
  post: Post;
  date: Date;
  imageSrc: string;
  format: string = 'd MMMM, h:mm:ss';

  constructor() {
  }

  ngOnInit() {
    this.date = new Date(this.post.timeCreation);
    this.checkMainPhoto();
  }

  checkMainPhoto() {
    if (this.post.user.mainPhoto == null) {
      this.imageSrc = "../../../../../../assets/images/default-user-icon.png"
    } else this.imageSrc = "data:image/png;base64," + this.post.user.mainPhoto;
  }

}
