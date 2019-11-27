import {Component, OnInit} from "@angular/core";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Observable, Subscription} from "rxjs";
import {PostService} from "../../../../services/post.service";
import {Router} from "@angular/router";
import {TagService} from "../../../../services/tag.service";
import {Tag} from "../../../../models/tag/tag";
import {Post} from "../../../../models/post/post";
import {tap} from "rxjs/operators";


@Component({
  selector: "app-createPost",
  templateUrl: "./createPost.component.html"
})
export class CreatePostComponent implements OnInit {

  base64ImageTextString: string;
  createPostForm: FormGroup;
  allTags: Tag[];
  postTags: Tag[] = [];
  private subscriptions: Subscription[] = [];

  constructor(private formBuilder: FormBuilder, private postService: PostService,
              private router: Router, private tagsService: TagService) {
  }

  ngOnInit() {
    console.log(JSON.parse(localStorage.getItem("user")));
    this.getAllTags();
    this.createPostForm = this.formBuilder.group({
      "title": [''],
      "tags": ['', Validators.pattern("^(#[0-9A-Za-z]+)*$")],
      "photoPath": ['', [Validators.required]],
      "description": [''],
      "user": [''],
      "timeCreation": ['']
    })

  }

  onCreate(post) {
    post.timeCreation = new Date();
    post.user = JSON.parse(localStorage.getItem("user"));
    post.description = 'Anime (pronounced AH-nee-may ) is a term for a style of Japanese comic book and video cartoon animation in which the main characters have large doe-like eyes. Many Web sites are devoted to anime. Anime is the prevalent style in Japanese comic books or manga . In Japan, the comic book is a popular form of entertainment for adults as well as for younger audiences. Story lines are often very sophisticated and complex and extend into episodic series. Typical anime themes or genres include Ninja and other martial arts; the supernatural or horror story; the romance; and science fiction including robots and space ships. Foils for the main characters, including robots, monsters, or just plain bad people, often lack the doe-eyed quality.'
    this.saveTags(post.tags);
    this.savePost(post);
    this.base64ImageTextString = null;
    this.createPostForm.reset()
    this.router.navigate(['/home'])
  }

  savePost(post) {
    let tags = this.splitTags(post.tags);
    tags.forEach(tag => {
      this.getTagByTitle(tag.toLowerCase());
    })
    post.photoPath = this.base64ImageTextString;
    this.postService.savePost(post).subscribe(post => {
      this.saveTagsForPost(post, this.postTags);
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

  saveTags(postTags: string){
    if (postTags != '') {
      this.getAllTags();
      let tags = this.splitTags(postTags);
      tags.forEach(newTag => {
        if (!this.allTags.filter(tag => newTag.toLowerCase() === tag.title.toLowerCase()).length) {
          let tmpTag = new Tag();
          tmpTag.title = newTag;
          this.tagsService.saveTag(tmpTag).subscribe();
        }
      })
    }
  }

  saveTagsForPost(post: Post, tags: Tag[]) {
    tags.forEach(tag => {
      this.postService.saveTag(post.id, tag.id).subscribe();
    })
  }

  getAllTags() {
    this.tagsService.getTags().subscribe(tags => {
      this.allTags = tags as Tag[];
    })
  }

  getTagByTitle(title: string) {
    this.tagsService.getTagByTitle(title).subscribe(tag => {
      console.log(tag)
      this.postTags.push(tag);
    })
  }

  splitTags(tags): string[] {
    return tags.split("#").filter(tag => tag != '');
  }

}
