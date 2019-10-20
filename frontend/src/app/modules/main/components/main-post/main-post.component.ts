import {Component, Input, OnInit} from "@angular/core";
import {MainPostModel} from "../../../../models/main-post/main-post.model";

@Component({
  selector: "app-main-post",
  templateUrl: "./main-post.component.html"
})
export class MainPostComponent implements OnInit {

  @Input()
  mainPost: MainPostModel;

  constructor() {
  }

  ngOnInit() {

  }
}
