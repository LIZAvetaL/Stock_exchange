import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { User } from 'src/app/model/user';
import { Observable } from 'rxjs/internal/Observable';

const baseUrl = 'http://localhost:8080/user';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  constructor(private http: HttpClient) {
  }

  getAll(): Observable<User[]> {

    return this.http.get<User[]>(`${baseUrl}`);
  }

  get(id) {
    return this.http.get(`${baseUrl}/${id}`);
  }

  save(user:User) {
    return this.http.post<User>(baseUrl, user);
  }
  update(id, data) {
    return this.http.put(`${baseUrl}/${id}`, data);
  }

  delete(id) {
    return this.http.delete(`${baseUrl}/${id}`);
  }

  deleteAll() {
    return this.http.delete(baseUrl);
  }

}
