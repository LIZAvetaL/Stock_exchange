import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {CreateBid} from '../../model/create-bid';

const baseUrl = 'http://localhost:8080/bid/';
const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json',
    Authorization: 'my-auth-token'
  })
};

@Injectable({
  providedIn: 'root'
})
export class BidService {

  constructor(private http: HttpClient) {
  }

  getAllBids(page: number, size: number, clientId: number): Observable<any> {
    return this.http.get(baseUrl + 'find/clients-bids' + `?page=${page}&size=${size}&clientId=${clientId}`);
  }

  create(clientId: number, createBid: CreateBid) {
    const body = {
      issuer: createBid.issuer,
      amount: createBid.amount,
      minPrice: createBid.minPrice,
      maxPrice: createBid.maxPrice,
      priority: createBid.priority,
      status: createBid.status,
      dueDate: createBid.dueDate,
      broker: createBid.broker
    };
    console.log(body);
    return this.http.post(baseUrl + 'create' + `?id=${clientId}`, body);
  }
}
