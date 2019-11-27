import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {User} from "../models/user/user";

@Injectable({
  providedIn: 'root'
})
export class UserService { //todo create interface

  constructor(private http: HttpClient) {
  }

  getUsers(): Observable<User[]> {
    return this.http.get<User[]>('/api/users');
  }

  saveUser(user: User): Observable<User> {
    return this.http.post<User>('/api/users', user);
  }

  deleteUser(id: string): Observable<void> {
    return this.http.delete<void>('/api/users/' + id);
  }

  getUserByUsername(username: string): Observable<User> {
    return this.http.get<User>('/api/users/?username=' + username);
  }

  getUserById(id: number): Observable<User> {
    return this.http.get<User>('/api/users/' + id);
  }
}
