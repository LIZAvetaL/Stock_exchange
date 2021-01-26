import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

const baseUrl = 'http://localhost:8080/broker/';

@Injectable({
  providedIn: 'root'
})
export class BrokerService {

  constructor(private http: HttpClient) { }

  getAll(title: string, page: number, size: number, sort: string): Observable<any> {
    return this.http.get(baseUrl + 'find/unemployed' + `?title=${title}&page=${page}&size=${size}&sort=${sort}`);
  }
  getClientsBrokers(clientId: number) : Observable<any>{
    return this.http.get(baseUrl + 'find' + `?client-id=${clientId}`);
  }
}
