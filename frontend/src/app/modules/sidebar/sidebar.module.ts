import {NgModule} from "@angular/core";
import {BlurbComponent} from "./component/blurb/blurb.component";
import {IntroComponent} from "./component/intro/intro.component";
import {MiniPostsComponent} from "./component/mini-posts/mini-posts.component";
import {PostsComponent} from "./component/posts/posts.component";
import {SidebarComponent} from "./component/sidebar.component";
import {FooterModule} from "./component/footer/footer.module";
import {CommonModule} from "@angular/common";
import {MiniPostComponent} from "./component/mini-posts/mini-post/mini-post.component";
import {RouterModule} from "@angular/router";

@NgModule({
  declarations: [BlurbComponent,IntroComponent,MiniPostsComponent,PostsComponent,SidebarComponent,MiniPostComponent],
  imports: [FooterModule,CommonModule,RouterModule],
  exports: [SidebarComponent]
})
export class SidebarModule {

}
