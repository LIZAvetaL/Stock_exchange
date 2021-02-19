import {Component, OnInit} from '@angular/core';
import {Bid} from '../../model/bid';
import {BidService} from '../../services/bid/bid.service';
import {TokenStorageService} from '../../services/token-storage/token-storage.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  bids: Bid[];

  title = '';
  page = 1;
  count = 0;
  pageSize = 3;
  pageSizes = [3, 6, 9];
  sort: string[];
  sortMap: Map<string, string>;

  constructor(private bidService: BidService,
              private tokenStorage: TokenStorageService
  ) {
    this.sort = ['asc', 'bidNumber'];
    this.sortMap = new Map();
  }

  ngOnInit() {
    this.retrieveBids();
  }

  retrieveBids() {
    this.bidService.get(this.title, this.page - 1, this.pageSize, this.sort)
      .subscribe(
        response => {
          const {content, totalElements} = response;
          this.bids = content;
          this.count = totalElements;
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
    this.retrieveBids();
  }
}
