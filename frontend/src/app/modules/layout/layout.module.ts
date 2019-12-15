import { NgModule } from "@angular/core";
import {BillingDetailsViewComponent} from "./components/billing-details/billing-details-view.component";
import {NotFoundComponent} from "./components/404/not-found.component";
import {HomeComponent} from "./components/home/home.component";
import {BillingAccountModule} from "../billing-account/billing-account.module";
import {HeaderModule} from "../header/header.module";
import {RouterModule} from "@angular/router";
import {MainModule} from "../main/main.module";
import {SidebarModule} from "../sidebar/sidebar.module";
import {SinglePostComponent} from "./components/single-post/single-post.component";
import {FooterComponent} from "../sidebar/component/footer/footer.component";
import {FooterModule} from "../sidebar/component/footer/footer.module";
import {LoginComponent} from "./components/login/login.component";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {CommonModule} from "@angular/common";
import {CreatePostComponent} from "./components/createPost/createPost.component";
import {MainPostComponent} from "../main/components/main-post/main-post.component";
import {ProfileComponent} from "./components/profile/profile.component";


@NgModule({
  declarations: [
    HomeComponent,
    NotFoundComponent,
    BillingDetailsViewComponent,
    SinglePostComponent,
    LoginComponent,
    CreatePostComponent,
    ProfileComponent
  ],
  imports: [
    BillingAccountModule,
    HeaderModule,
    RouterModule,
    SidebarModule,
    FormsModule,
    MainModule,
    FooterModule,
    FormsModule,
    CommonModule,
    ReactiveFormsModule,
  ],
  providers: [],
  exports: [HomeComponent, NotFoundComponent, BillingDetailsViewComponent, SinglePostComponent, LoginComponent, CreatePostComponent, ProfileComponent]
})
export class LayoutModule {}
