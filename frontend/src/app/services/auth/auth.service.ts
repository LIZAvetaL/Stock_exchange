import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';

const AUTH_API = 'http://localhost:8080/auth/';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient) {
  }

  login(credentials): Observable<any> {
    return this.http.post(AUTH_API + 'sign-in', {
      email: credentials.email,
      password: credentials.password
    });
  }

  register(user): Observable<any> {
    return this.http.post(AUTH_API + 'sign-up', {
      name: user.name,
      email: user.email,
      password: user.password,
      role: user.role,
      exchange: user.exchange
    });
  }
}
