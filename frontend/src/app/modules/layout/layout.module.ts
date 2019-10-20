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


@NgModule({
  declarations: [
    HomeComponent,
    NotFoundComponent,
    BillingDetailsViewComponent,
    SinglePostComponent,
    LoginComponent
  ],
  imports: [
    BillingAccountModule,
    HeaderModule,
    RouterModule,
    SidebarModule,
    MainModule,
    FooterModule
  ],
  providers: [],
  exports: [HomeComponent, NotFoundComponent, BillingDetailsViewComponent, SinglePostComponent, LoginComponent]
})
export class LayoutModule {}
