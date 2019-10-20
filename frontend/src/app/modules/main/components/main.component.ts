import {Component, OnInit} from "@angular/core";
import {MainPostModel} from "../../../models/main-post/main-post.model";

@Component({
  selector: "app-main",
  templateUrl: "./main.component.html"
})
export class MainComponent implements OnInit {

  public mainPosts: MainPostModel[] = [];

  ngOnInit(): void {
    this.mainPosts = [{

      title1: "Magna sed adipiscing",
      title2: "Lorem ipsum dolor amet nullam consequat etiam feugiat",
      datetime: "November 1, 2015",
      authorName: "Jane Doe",
      avatarPath: "../../../../assets/images/avatar.jpg",
      imagePath: "../../../../assets/images/pic01.jpg",
      postInfo: "Mauris neque quam, fermentum ut nisl vitae, convallis maximus nisl. Sed mattis nunc id lorem euismod placerat. Vivamus porttitor magna enim, ac accumsan tortor cursus at. Phasellus sed ultricies mi non congue ullam corper. Praesent tincidunt sed tellus ut rutrum. Sed vitae justo condimentum, porta lectus vitae, ultricies congue gravida diam non fringilla",
      likes: 28,
      comments: 128

    }, {
      title1: "Magna sed adipiscing",
      title2: "Lorem ipsum dolor amet nullam consequat etiam feugiat",
      datetime: "November 1, 2015",
      authorName: "Jane Doe",
      avatarPath: "../../../../assets/images/avatar.jpg",
      imagePath: "../../../../assets/images/pic01.jpg",
      postInfo: "Mauris neque quam, fermentum ut nisl vitae, convallis maximus nisl. Sed mattis nunc id lorem euismod placerat. Vivamus porttitor magna enim, ac accumsan tortor cursus at. Phasellus sed ultricies mi non congue ullam corper. Praesent tincidunt sed tellus ut rutrum. Sed vitae justo condimentum, porta lectus vitae, ultricies congue gravida diam non fringilla",
      likes: 28,
      comments: 128
    }];
  }
}
