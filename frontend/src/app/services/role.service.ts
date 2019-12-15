import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Role} from "../models/role";

@Injectable({
  providedIn: 'root'
})
export class RoleService { //todo create interface

  constructor(private http: HttpClient) {
  }

  getRoles(): Observable<Role[]> {
    return this.http.get<Role[]>('/api/roles');
  }

  saveRole(role: Role): Observable<Role> {
    return this.http.post<Role>('/api/roles', role);
  }

  deleteRole(id: string): Observable<void> {
    return this.http.delete<void>('/api/roles/' + id);
  }

  getRoleByTitle(title: string): Observable<Role> {
    return this.http.get<Role>('/api/roles/role?title=' + title);
  }
}
