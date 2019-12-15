import {NgModule} from "@angular/core";
import {MainComponent} from "./components/main.component";
import {MainPostComponent} from "./components/main-post/main-post.component";
import {CommonModule} from "@angular/common";
import {RouterModule} from "@angular/router";
import {NgxPaginationModule} from "ngx-pagination";
import {FormsModule} from "@angular/forms";

@NgModule({
  declarations: [
    MainComponent,
    MainPostComponent
  ],
  imports: [
    CommonModule,
    RouterModule,
    FormsModule,
    NgxPaginationModule
  ],
  providers: [],
  exports: [MainComponent, MainPostComponent]
})
export class MainModule {
}
