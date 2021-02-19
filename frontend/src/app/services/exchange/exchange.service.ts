import {HttpClient} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {MessageResponse} from '../../model/messageResponse';
import {StockExchange} from '../../model/StockExchange';
import {CreateExchange} from "../../model/create-exchange";

const API_URL = 'http://localhost:8080/exchange/';

@Injectable({
  providedIn: 'root'
})
export class ExchangeService {

  constructor(private http: HttpClient) {
  }

  getExchange(ownerId: number): Observable<any> {
    return this.http.get(API_URL + `${ownerId}`);
  }

  get(id: number): Observable<any> {
    return this.http.get(API_URL + 'find/' + `${id}`);
  }

  changeStatus(id: number, status: string): Observable<MessageResponse> {
    return this.http.post<MessageResponse>(API_URL + 'change-status' + `?id=${id}&status=${status}`, null);
  }

  updateExchange(stockExchange: StockExchange): Observable<MessageResponse> {
    console.log(stockExchange);
    return this.http.post<MessageResponse>('http://localhost:8080/exchange/update', stockExchange);
  }

  saveExchange(exchange: CreateExchange, id: number): Observable<MessageResponse> {
    console.log(exchange);
    return this.http.post<MessageResponse>(API_URL + 'create' + '?owner-id=' + id, exchange);
  }

  getAll(): Observable<any> {
    return this.http.get(API_URL + 'find/all');
  }
}
