import {Component, OnInit} from "@angular/core";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Observable, Subscription} from "rxjs";
import {PostService} from "../../../../services/post.service";
import {Router} from "@angular/router";
import {TagService} from "../../../../services/tag.service";
import {Tag} from "../../../../models/tag";
import {Post} from "../../../../models/post";
import {tap} from "rxjs/operators";
import {StorageService} from "../../../../services/storage.service";


@Component({
  selector: "app-createPost",
  templateUrl: "./createPost.component.html"
})
export class CreatePostComponent implements OnInit {

  base64ImageTextString: string;
  createPostForm: FormGroup;
  postTags: Tag[] = [];
  private subscriptions: Subscription[] = [];

  constructor(private formBuilder: FormBuilder, private postService: PostService,
              private router: Router, private storageService: StorageService) {
  }

  ngOnInit() {
    this.createPostForm = this.formBuilder.group({
      "title": [''],
      "postTags": ['', Validators.pattern("^(#[0-9A-Za-z]+)*$")],
      "photoPath": ['', [Validators.required]],
      "description": [''],
      "user": [''],
      "timeCreation": ['']
    })

  }

  onCreate(post) {
    post.timeCreation = new Date();
    post.user = this.storageService.getCurrentUser();
    this.savePost(post);
  }

  savePost(post) {
    let tags = post.postTags;
    post.postTags = [];
    this.splitTags(tags).forEach(tag => {
      let tmpTag = new Tag();
      tmpTag.title = tag;
      post.postTags.push(tmpTag);
    });
    post.photoPath = this.base64ImageTextString;
    console.log(post.postTags);
    this.postService.createPost(post).subscribe(post => {
      this.router.navigate(['/home'])
    });
  }


  handleFileSelect(evt) {
    let files = evt.target.files;
    let file = files[0];

    if (files && file) {
      let reader = new FileReader();

      reader.onload = this._handleReaderLoaded.bind(this);

      reader.readAsBinaryString(file);
    }
  }

  _handleReaderLoaded(readerEvt) {
    let binaryString = readerEvt.target.result;
    this.base64ImageTextString = btoa(binaryString);
  }

  splitTags(tags): string[] {
    console.log(tags.split("#").filter(tag => tag != ''));
    return tags.split("#").filter(tag => tag != '');
  }

}
