import {User} from "./user";
import {Post} from "./post";
import {Timestamp} from "rxjs";

export class Comment {

  id: number;
  message: string;
  post: Post;
  user: User;
  timeCreation: Date;

}
