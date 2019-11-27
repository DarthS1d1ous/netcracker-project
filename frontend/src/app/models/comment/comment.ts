import {User} from "../user/user";
import {Post} from "../post/post";
import {Timestamp} from "rxjs";

export interface Comment {

  id: number;
  message: string;
  post: Post;
  user: User;
  timeCreation: Timestamp<string>;

}
