import {Component, OnInit} from "@angular/core";
import {User} from "../../../../models/user/user";

@Component({
  selector:"app-intro",
  templateUrl:"./intro.component.html"
})
export class IntroComponent implements OnInit {

  imageSrc: string;
  user: User =  JSON.parse(localStorage.getItem("user"));
  constructor( ) {}

  ngOnInit() {
    this.checkMainPhoto()
  }

  checkMainPhoto() {
    if (this.user.mainPhoto === "") {
      this.imageSrc = "../../../../../../assets/images/default-user-icon.png"
    } else this.imageSrc = "data:image/png;base64," + this.user.mainPhoto;
  }
}
