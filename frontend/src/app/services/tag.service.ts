import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Tag} from "../models/tag";

@Injectable({
  providedIn: 'root'
})
export class TagService { //todo create interface

  constructor(private http: HttpClient) {
  }

  getTags(): Observable<Tag[]> {
    return this.http.get<Tag[]>('/api/tags');
  }

  saveTag(tag: Tag): Observable<Tag> {
    return this.http.post<Tag>('/api/tags', tag);
  }

  deleteTag(id: string): Observable<void> {
    return this.http.delete<void>('/api/tags/' + id);
  }

  getTagByTitle(title: string): Observable<Tag> {
    return this.http.get<Tag>('/api/tags/tag?title=' + title);
  }

  getTagById(id: number): Observable<Tag> {
    return this.http.get<Tag>('/api/tags/' + id);
  }
}
