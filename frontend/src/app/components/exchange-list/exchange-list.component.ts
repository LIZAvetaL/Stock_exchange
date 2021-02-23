import {Component, OnInit} from '@angular/core';
import {StockExchange} from 'src/app/model/StockExchange';
import {ExchangeService} from 'src/app/services/exchange/exchange.service';
import {TokenStorageService} from 'src/app/services/token-storage/token-storage.service';

@Component({
  selector: 'app-exchange-list',
  templateUrl: './exchange-list.component.html',
  styleUrls: ['./exchange-list.component.css']
})
export class ExchangeListComponent implements OnInit {

  private exchanges: StockExchange[];
  private ownerId: number;

  page = 1;
  count = 0;
  pageSize = 3;
  pageSizes = [3, 6, 9];
  sort: string[];
  sortMap: Map<string, string>;
  currentItem: number;

  constructor(
    private exchangeService: ExchangeService,
    private tokenStorage: TokenStorageService
  ) {
    this.ownerId = tokenStorage.getUser().id;
    this.sort = [];
    this.sortMap = new Map();
  }

  ngOnInit() {
    this.retrieveExchanges();
  }

  retrieveExchanges() {
    this.exchangeService.getExchange(this.page - 1, this.pageSize, this.sort, this.ownerId)
      .subscribe(
        response => {
          const {content, totalElements} = response;
          this.exchanges = content;
          this.count = totalElements;
          this.currentItem = this.page * this.pageSize;
        },
        error => {
          console.log(error);
        });

  }

  handlePageChange(event) {
    this.page = event;
    this.retrieveExchanges();
  }

  handlePageSizeChange(event) {
    this.pageSize = event.target.value;
    this.page = 1;
    this.retrieveExchanges();
  }

  sortTable(columnName: string) {
    if (this.sortMap.has(columnName)) {
      if (this.sortMap.get(columnName) === 'desc') {
        this.sortMap.delete(columnName);
      } else {
        this.sortMap.set(columnName, 'desc');
      }
    } else {
      this.sortMap.set(columnName, 'asc');
    }

    this.sort = [];
    let index = 0;
    for (const [key, value] of this.sortMap) {
      this.sort[index++] = value;
      this.sort[index++] = key;
    }
    this.retrieveExchanges();
  }

}
