import {Component, OnInit} from '@angular/core';
import {Bid} from 'src/app/model/bid';
import {TokenStorageService} from 'src/app/services/token-storage/token-storage.service';
import {BidService} from '../../services/bid/bid.service';

@Component({
  selector: 'app-bid-list',
  templateUrl: './client-bid-list.component.html',
  styleUrls: ['./client-bid-list.component.css']
})
export class ClientBidListComponent implements OnInit {

  bids: Bid[];
  clientId: number;

  page = 1;
  count = 0;
  pageSize = 3;
  pageSizes = [3, 6, 9];
  sort: string[];
  sortMap: Map<string, string>;

  constructor(private bidService: BidService,
              private tokenStorage: TokenStorageService
  ) {
    this.clientId = tokenStorage.getUser().id;
    this.sort = [];
    this.sortMap = new Map();
  }

  ngOnInit() {
    this.retrieveBrokers();
  }

  retrieveBrokers() {
    this.bidService.getAllBids(this.page - 1, this.pageSize, this.sort, this.clientId)
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
