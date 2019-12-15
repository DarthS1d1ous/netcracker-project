import {BrowserModule} from "@angular/platform-browser";
import {NgModule} from "@angular/core";
import {BsDropdownModule} from "ngx-bootstrap/dropdown";
import {TooltipModule} from "ngx-bootstrap/tooltip";
import {ModalModule} from "ngx-bootstrap/modal";
import {FormsModule} from "@angular/forms";

import {AppComponent} from "./app.component";
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import {Ng4LoadingSpinnerModule} from "ng4-loading-spinner";
import {RouterModule, Routes} from "@angular/router";
import {NotFoundComponent} from "./modules/layout/components/404/not-found.component";
import {LayoutModule} from "./modules/layout/layout.module";
import {HomeComponent} from "./modules/layout/components/home/home.component";
import {MainModule} from "./modules/main/main.module";
import {SidebarModule} from "./modules/sidebar/sidebar.module";
import {SinglePostComponent} from "./modules/layout/components/single-post/single-post.component";
import {LoginComponent} from "./modules/layout/components/login/login.component";
import {CreatePostComponent} from "./modules/layout/components/createPost/createPost.component";

import {NgxPaginationModule} from 'ngx-pagination';
import {APIInterceptor} from "./interceptors/api-interceptor";
import {UserService} from "./services/user.service";
import {ProfileComponent} from "./modules/layout/components/profile/profile.component";
import {LoginGuard} from "./services/login.guard";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {MatDialogModule} from '@angular/material/dialog';

const appRoutes: Routes = [
  {path: "", component: HomeComponent, canActivate: [LoginGuard]},
  {path: "home", component: HomeComponent, canActivate: [LoginGuard]},
  {path: "login", component: LoginComponent},
  {path: "posts/:id", component: SinglePostComponent, canActivate: [LoginGuard]},
  {path: "createPost", component: CreatePostComponent, canActivate: [LoginGuard]},
  {path: "profile/:id", component: ProfileComponent, canActivate: [LoginGuard]},
  {path: "**", component: NotFoundComponent}
];

@NgModule({
  declarations: [
    AppComponent,
  ],
  imports: [
    BrowserModule,
    FormsModule,
    LayoutModule,
    HttpClientModule,
    Ng4LoadingSpinnerModule.forRoot(),
    BsDropdownModule.forRoot(),
    TooltipModule.forRoot(),
    ModalModule.forRoot(),
    RouterModule.forRoot(appRoutes),
    SidebarModule,
    MainModule,
    NgxPaginationModule
  ],
  providers: [UserService, APIInterceptor, {
    provide: HTTP_INTERCEPTORS,
    useClass: APIInterceptor,
    multi: true
  }, LoginGuard],
  bootstrap: [AppComponent]
})
export class AppModule {
}
