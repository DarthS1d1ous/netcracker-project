import { Component, OnInit } from "@angular/core";
import {StorageService} from "../../../services/storage.service";
import {User} from "../../../models/user";
import {Router} from "@angular/router";

@Component({
  selector: "app-header",
  templateUrl: "./header.component.html"
})
export class HeaderComponent implements OnInit {

  user: User = this.storageService.getCurrentUser();
  url: string = 'home';

  constructor(private storageService: StorageService, private router: Router) {}

  ngOnInit() {
    this.user = this.storageService.getCurrentUser();
    if(this.user == null){
      this.url = 'login';
    }
  }

  LogOut(){
    this.storageService.clearToken();
    this.storageService.clearUser();
    this.router.navigate(['/login'])
  }
}
