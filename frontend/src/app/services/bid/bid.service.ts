import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {CreateBid} from '../../model/create-bid';
import {Bid} from "../../model/bid";
import {MessageResponse} from "../../model/messageResponse";

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

  getAllBids(page: number, size: number, sort: string[], clientId: number): Observable<any> {
    return this.http.get(baseUrl + 'find/clients-bids' + `?page=${page}&size=${size}&sort=${sort}&clientId=${clientId}`);
  }

  getAllBrokerBids(page: number, size: number, sort: string[], clientId: number): Observable<any> {
    return this.http.get(baseUrl + 'find/brokers-bids' + `?page=${page}&size=${size}&sort=${sort}&brokerId=${clientId}`);
  }

  create(clientId: number, createBid: CreateBid) {
    return this.http.post(baseUrl + 'create' + `?id=${clientId}`, createBid);
  }

  update(editBid: Bid): Observable<MessageResponse> {
    return this.http.post<MessageResponse>(baseUrl + 'update', editBid);
  }

  getBid(bidId: number): Observable<Bid> {
    return this.http.get<Bid>(baseUrl + 'find' + `?id=${bidId}`);
  }

  getBidsForCreateDeal(page: number, size: number, sort: string[], bidId: number): Observable<any> {
    return this.http.get(baseUrl + 'find/bids-for-deal' + `?page=${page}&size=${size}&sort=${sort}&bidId=${bidId}`);
  }

  createDeal(bidId: number, sellerBidId: number, bid: Bid, price: number) {
    return this.http.post(baseUrl + 'create-deal/' + bidId + '/' + sellerBidId + '/' + price, null);
  }

  get(page: number, size: number, sort: string[]): Observable<any> {
    return this.http.get(baseUrl + 'find/all' + `?page=${page}&size=${size}&sort=${sort}`);
  }
}
