import {HttpClient, HttpParams} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {UnemployedBroker} from '../../model/unemployed-broker';
import {Broker} from '../../model/broker';

const baseUrl = 'http://localhost:8080/broker/';

@Injectable({
  providedIn: 'root'
})
export class BrokerService {

  constructor(private http: HttpClient) {
  }

  getAll(title: string, page: number, size: number, sort: string): Observable<any> {
    return this.http.get(baseUrl + 'find/unemployed' + `?title=${title}&page=${page}&size=${size}&sort=${sort}`);
  }

  getClientsBrokers(clientId: number): Observable<Broker[]> {
    return this.http.get<Broker[]>(baseUrl + 'find' + `?client-id=${clientId}`);
  }

  employ(brokerId: number, clientId: number) {
    return this.http.post(baseUrl + 'employ/' + brokerId + '/' + clientId, null);
  }
}
