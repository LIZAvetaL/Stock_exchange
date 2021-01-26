import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

const baseUrl = 'http://localhost:8080/bid/';

@Injectable({
  providedIn: 'root'
})
export class ClientService {

  constructor(private http: HttpClient) { }

  getAllBids(page: number, size: number, clientId: number): Observable<any> {
    return this.http.get(baseUrl + 'find/clients-bids' + `?page=${page}&size=${size}&clientId=${clientId}`);
  }
}
