import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Deal} from "../../model/deal";

const API_URL = 'http://localhost:8080/deal/';

@Injectable({
  providedIn: 'root'
})
export class DealService {

  constructor(private http: HttpClient) {
  }

  getDeals(page: number, size: number, sort: string[], brokerId: number): Observable<any> {
    return this.http.get(API_URL + 'find' + `?page=${page}&size=${size}&sort=${sort}&broker-id=${brokerId}`);
  }
}
