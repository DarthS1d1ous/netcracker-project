import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {User} from "../models/user";
import {LoginModel} from "../models/login.model";

@Injectable({
  providedIn: 'root'
})
export class UserService { //todo create interface

  constructor(private http: HttpClient) {
  }

  generateToken(login: LoginModel): Observable<AuthToken> {
    return this.http.post<AuthToken>("/api/token/generate-token", login);
  }

  getAuthorizedUser(): Observable<User> {
    return this.http.get<User>("/api/users/current");
  }

  findUserLikesCount(id : number): Observable<number> {
    return this.http.get<number>('/api/users/likes/count/' + id);
  }

  getUsers(): Observable<User[]> {
    return this.http.get<User[]>('/api/users');
  }

  saveUser(user: User): Observable<User> {
    return this.http.post<User>('/api/users', user);
  }

  deleteUser(id: number): Observable<void> {
    return this.http.delete<void>('/api/users/' + id);
  }

  getUserByUsername(username: string): Observable<User> {
    return this.http.get<User>('/api/users/user?username=' + username);
  }

  getUserById(id: number): Observable<User> {
    return this.http.get<User>('/api/users/' + id);
  }
}

export interface AuthToken {
  readonly token: string;
}
