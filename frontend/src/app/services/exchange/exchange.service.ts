import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

const API_URL = 'http://localhost:8080/exchange/';

@Injectable({
  providedIn: 'root'
})
export class ExchangeService {

  constructor(private http: HttpClient) { }

  getExchange(ownerId: number): Observable<any> {
    return this.http.get(API_URL + `${ownerId}`);
  }

  get(id: number): Observable<any> {
    return this.http.get(API_URL +"find/"+ `${id}`);
  }
}
