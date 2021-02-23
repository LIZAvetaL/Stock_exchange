import {HttpClient, HttpParams} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {UnemployedBroker} from '../../model/unemployed-broker';
import {Broker} from '../../model/broker';
import {BrokerStatistics} from "../../model/BrokerStatistics";
import {StockExchange} from "../../model/StockExchange";

const baseUrl = 'http://localhost:8080/broker/';

@Injectable({
  providedIn: 'root'
})
export class BrokerService {

  constructor(private http: HttpClient) {
  }

    getAll(title: string, page: number, size: number, sort: string[]): Observable<any> {
    return this.http.get(baseUrl + 'find/unemployed' + `?title=${title}&page=${page}&size=${size}&sort=${sort}`);
  }

  getClientsBrokers(page: number, size: number, clientId: number): Observable<any> {
    return this.http.get(baseUrl + 'find/all' + `?page=${page}&size=${size}&client-id=${clientId}`);
  }

  getBrokers(clientId: number): Observable<any> {
    return this.http.get(baseUrl + 'find' + `?client-id=${clientId}`);
  }

  employ(brokerId: number, clientId: number): Observable<any> {
    return this.http.post(baseUrl + 'employ/' + brokerId + '/' + clientId, null);
  }

  dismiss(brokerId: number): Observable<any> {
    return this.http.post(baseUrl + 'dismiss/' + brokerId, null);
  }

  getStatistics(page: number, size: number, clientId: number): Observable<any> {
    return this.http.get(baseUrl + 'find/statistics' + `?page=${page}&size=${size}&clientId=${clientId}`);

  }

  update(id: number, exchange: string): Observable<any> {
    return this.http.post(baseUrl + 'update/' + id + '/' + exchange, null);
  }
}
