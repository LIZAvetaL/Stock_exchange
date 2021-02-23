import { Component, OnInit } from '@angular/core';
import {Bid} from "../../model/bid";
import {BidService} from "../../services/bid/bid.service";
import {TokenStorageService} from "../../services/token-storage/token-storage.service";

@Component({
  selector: 'app-broker-bid-list',
  templateUrl: './broker-bid-list.component.html',
  styleUrls: ['./broker-bid-list.component.css']
})
export class BrokerBidListComponent implements OnInit {

  bids: Bid[];
  brokerId: number;

  page = 1;
  count = 0;
  pageSize = 3;
  pageSizes = [3, 6, 9];
  sort: string[];
  sortMap: Map<string, string>;

  constructor(private bidService: BidService,
              private tokenStorage: TokenStorageService
  ) {
    this.brokerId = tokenStorage.getUser().id;
    this.sort = [];
    this.sortMap = new Map();
  }

  ngOnInit() {
    this.retrieveBrokers();
  }

  retrieveBrokers() {
    this.bidService.getAllBrokerBids(this.page - 1, this.pageSize, this.sort, this.brokerId)
      .subscribe(
        response => {
          const {content, totalElements} = response;
          this.bids = content;
          this.count = totalElements;
          console.log(response);
        },
        error => {
          console.log(error);
        });

  }

  handlePageChange(event) {
    this.page = event;
    this.retrieveBrokers();
  }

  handlePageSizeChange(event) {
    this.pageSize = event.target.value;
    this.page = 1;
    this.retrieveBrokers();
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
    this.retrieveBrokers();
  }
}
