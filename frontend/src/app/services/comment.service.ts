import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Comment} from "../models/comment/comment";

@Injectable({
  providedIn: 'root'
})
export class CommentService { //todo create interface

  constructor(private http: HttpClient) {
  }

  getComments(): Observable<Comment[]> {
    return this.http.get<Comment[]>('/api/comments');
  }

  saveComment(comment: Comment): Observable<Comment> {
    return this.http.post<Comment>('/api/comments', comment);
  }

  deleteComment(id: string): Observable<void> {
    return this.http.delete<void>('/api/comments/' + id);
  }

  getCommentById(id: number): Observable<Comment> {
    return this.http.get<Comment>('/api/comments/' + id);
  }
}
