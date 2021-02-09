import { Component, OnInit } from '@angular/core';
import {Bid} from "../../model/bid";
import {BidService} from "../../services/bid/bid.service";
import {TokenStorageService} from "../../services/token-storage/token-storage.service";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  bids: Bid[];

  page = 1;
  count = 0;
  pageSize = 3;
  pageSizes = [3, 6, 9];
  sort: string[];

  constructor(private bidService: BidService,
              private tokenStorage: TokenStorageService
  ) {
    this.sort = ['desc', 'bidNumber'];
  }

  ngOnInit() {
    this.retrieveBids();
  }

  retrieveBids() {
    this.bidService.get(this.page - 1, this.pageSize, this.sort)
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
    this.retrieveBids();
  }

  handlePageSizeChange(event) {
    this.pageSize = event.target.value;
    this.page = 1;
    this.retrieveBids();
  }

  sortTable(columnName: string) {
    this.sort = ['asc', columnName];
    this.retrieveBids();
  }
}
