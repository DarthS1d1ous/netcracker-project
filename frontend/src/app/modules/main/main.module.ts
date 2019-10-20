import { NgModule } from "@angular/core";
import {MainComponent} from "./components/main.component";
import {MainPostComponent} from "./components/main-post/main-post.component";
import {CommonModule} from "@angular/common";


@NgModule({
  declarations: [
    MainComponent,
    MainPostComponent
  ],
  imports: [
    CommonModule
  ],
  providers: [],
  exports: [MainComponent]
})
export class MainModule {}
