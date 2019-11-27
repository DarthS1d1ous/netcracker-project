import {User} from "../user/user";
import {Tag} from "../tag/tag";
import {Comment} from "../comment/comment";

export interface Post {

  id: number;
  photoPath: string;
  description: string;
  timeCreation: Date;
  title: string;
  user: User;
  postTags: Tag[];
  comments: Comment[];
  userLikes: User[];

}
