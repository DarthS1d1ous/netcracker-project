import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Post} from "../models/post/post";
import {Page} from "../models/page/Page";

@Injectable({
  providedIn: 'root'
})
export class PostService { //todo create interface

  constructor(private http: HttpClient) {
  }

  // Ajax request for billing account data
  findPosts(page: number, size: number, direction: string, properties: string): Observable<Page> {
    return this.http.get<Page>('/api/posts?page=' + page + "&size=" + size + "&direction=" + direction +"&properties=" + properties);
  }

  savePost(post: Post): Observable<Post> {
    return this.http.post<Post>('/api/posts', post);
  }

  deletePost(id: number): Observable<void> {
    return this.http.delete<void>('/api/posts/' + id);
  }

  findPostById(id: number): Observable<Post> {
    return this.http.get<Post>('/api/posts/' + id);
  }

  findPostByTitle(title: string): Observable<Post> {
    return this.http.get<Post>('/api/posts?title=' + title);
  }

  findPostsCount(): Observable<number> {
    return this.http.get<number>('api/posts/count');
  }

  saveLike(postId: number, userId: number): Observable<boolean> {
    return this.http.post<boolean>('api/posts/'+ postId + '/' + userId + '/like', true);
  }

  deleteLike(postId: number, userId: number): Observable<boolean> {
    return this.http.delete<boolean>('api/posts/'+ postId + '/' + userId + '/like');
  }

  saveTag(postId: number, tagId: number): Observable<boolean> {
    return this.http.post<boolean>('api/posts/'+ postId + '/tags/' + tagId , true);
  }
}
