import {NgModule} from "@angular/core";
import {BlurbComponent} from "./component/blurb/blurb.component";
import {IntroComponent} from "./component/intro/intro.component";
import {MiniPostsComponent} from "./component/mini-posts/mini-posts.component";
import {PostsComponent} from "./component/posts/posts.component";
import {SidebarComponent} from "./component/sidebar.component";
import {FooterModule} from "./component/footer/footer.module";

@NgModule({
  declarations: [BlurbComponent,IntroComponent,MiniPostsComponent,PostsComponent,SidebarComponent],
  imports: [FooterModule],
  exports: [SidebarComponent]
})
export class SidebarModule {

}
