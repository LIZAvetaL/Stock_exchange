import {Component, OnInit} from '@angular/core';
import {StockExchange} from "../../model/StockExchange";
import {ExchangeService} from "../../services/exchange/exchange.service";
import {TokenStorageService} from "../../services/token-storage/token-storage.service";
import {OwnerStatistics} from "../../model/owner-statistics";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-exchange-statistics',
  templateUrl: './exchange-statistics.component.html',
  styleUrls: ['./exchange-statistics.component.css']
})
export class ExchangeStatisticsComponent implements OnInit {

  private statistics: OwnerStatistics[];
  private exchangeId: number;

  page = 1;
  count = 0;
  pageSize = 3;
  pageSizes = [3, 6, 9];
  currentItem: number;

  constructor(
    private exchangeService: ExchangeService,
    private router: ActivatedRoute
  ) {
    this.exchangeId = this.router.snapshot.params.id;
  }

  ngOnInit() {
    this.retrieveExchanges();
  }

  retrieveExchanges() {
    this.exchangeService.getStatistics(this.page - 1, this.pageSize, this.exchangeId)
      .subscribe(
        response => {
          console.log(response);
          const {content, totalElements} = response;
          this.statistics = content;
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
}
