import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {User} from '../model/user';
import {MessageResponse} from '../model/messageResponse';

const API_URL = 'http://localhost:8080/user/';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) {
  }

  getAll(page: number, size: number, sort: string[]): Observable<any> {
    return this.http.get(API_URL + `?page=${page}&size=${size}&sort=${sort}`);
  }

  get(id: number): Observable<any> {
    return this.http.get(API_URL + 'find/id/' + `${id}`);
  }

  update(id: number, role: string): Observable<MessageResponse> {
    return this.http.post<MessageResponse>(API_URL + 'update' + `?id=${id}&role=${role}`, null);
  }

  blockUser(id: number): Observable<MessageResponse> {
    return this.http.post<MessageResponse>(API_URL + 'block' + `?id=${id}`, null);
  }

  unblockUser(id: number): Observable<MessageResponse> {
    return this.http.post<MessageResponse>(API_URL + 'unblock' + `?id=${id}`, null);
  }
}
