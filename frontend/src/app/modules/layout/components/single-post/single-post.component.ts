import {Component, OnDestroy, OnInit} from "@angular/core";
import {ActivatedRoute} from "@angular/router";
import {Observable, Subscription} from "rxjs";
import {PostService} from "../../../../services/post.service";
import {Post} from "../../../../models/post/post";
import {tap} from "rxjs/operators";

@Component({
  selector: "app-single-post",
  templateUrl: "./single-post.component.html"
})
export class SinglePostComponent implements OnInit {

  constructor() {
  }

  ngOnInit() {
  }

}
