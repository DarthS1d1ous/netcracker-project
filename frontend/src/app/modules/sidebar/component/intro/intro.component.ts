import {Component, OnInit} from "@angular/core";
import {User} from "../../../../models/user";
import {StorageService} from "../../../../services/storage.service";
import {UserService} from "../../../../services/user.service";

@Component({
  selector:"app-intro",
  templateUrl:"./intro.component.html"
})
export class IntroComponent implements OnInit {

  imageSrc: string;
  user: User =  this.storageService.getCurrentUser();
  constructor(private storageService: StorageService, private userService: UserService) {}

  ngOnInit() {
    this.checkMainPhoto()
  }

  checkMainPhoto() {
    if (this.user.mainPhoto == null) {
      this.imageSrc = "../../../../../../assets/images/default-user-icon.png"
    } else this.imageSrc = "data:image/png;base64," + this.user.mainPhoto;
  }
}
