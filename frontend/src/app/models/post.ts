import {User} from "./user";
import {Tag} from "./tag";
import {Comment} from "./comment";

export interface Post {

  id: number;
  photoPath: string;
  description: string;
  timeCreation: Date;
  title: string;
  user: User;
  postTags: Tag[];
  userLikes: User[];
}
