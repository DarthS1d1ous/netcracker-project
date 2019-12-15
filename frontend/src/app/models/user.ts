import {Timestamp} from "rxjs";
import {Role} from "./role";

export interface User {

  id: number;
  email: string;
  password: string;
  name: string;
  timeRegistration: Timestamp<string>;
  surname: string;
  phone: string;
  username: string;
  mainPhoto: string;
  role: Role;
  // posts: any[];
  // postLikes: any[];
  // comments: any[];
  subscribers: User[];
  subscriptions: User[];

  // static cloneBase(user: User): User {
  //   const clonedUser: User = new User();
  //   clonedUser.id = user.id;
  //   clonedUser.username = user.username;
  //   clonedUser.email = user.email;
  //   clonedUser.surname = user.surname;
  //   clonedUser.timeRegistration = user.timeRegistration;
  //   clonedUser.comments = user.comments;
  //   clonedUser.name = user.name;
  //   clonedUser.password = user.password;
  //   clonedUser.phone = user.phone;
  //   clonedUser.role = user.role;
  //   clonedUser.posts = user.posts;
  //   clonedUser.postLikes = user.postLikes;
  //   clonedUser.subscribers = user.subscribers;
  //   clonedUser.subscriptions = user.subscriptions;
  //   return clonedUser;
  // }

}
