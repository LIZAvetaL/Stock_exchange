import {Component, OnInit} from '@angular/core';
import {Deal} from "../../model/deal";
import {TokenStorageService} from "../../services/token-storage/token-storage.service";
import {DealService} from "../../services/deal/deal.service";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-deal-list',
  templateUrl: './deal-list.component.html',
  styleUrls: ['./deal-list.component.css']
})
export class DealListComponent implements OnInit {

  deals: Deal[];
  brokerId: number;

  page = 1;
  count = 0;
  pageSize = 3;
  pageSizes = [3, 6, 9];
  sort: string[];
  sortMap: Map<string, string>;
  activeDael = 0;

  constructor(private dealService: DealService,
              private tokenStorage: TokenStorageService,
              private router: ActivatedRoute
  ) {
    this.brokerId = tokenStorage.getUser().id;
    this.sort = ['desc', 'bargainDate'];
    this.sortMap = new Map();
    if (this.router.snapshot.params.dealNumber !== undefined) {
      this.activeDael = this.router.snapshot.params.dealNumber;
    }
  }

  ngOnInit() {
    this.retrieveDeals();
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
    this.retrieveDeals();
  }

  handlePageChange(event) {
    this.page = event;
    this.retrieveDeals();
  }

  handlePageSizeChange(event) {
    this.pageSize = event.target.value;
    this.page = 1;
    this.retrieveDeals();
  }

  private retrieveDeals() {
    this.dealService.getDeals(this.page - 1, this.pageSize, this.sort, this.brokerId)
      .subscribe(
        response => {
          const {content, totalElements} = response;
          this.deals = content;
          this.count = totalElements;
          console.log(response);
        },
        error => {
          console.log(error);
        });
  }
}
