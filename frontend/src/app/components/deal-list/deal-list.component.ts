import {Component, OnInit} from '@angular/core';
import {Deal} from "../../model/deal";
import {TokenStorageService} from "../../services/token-storage/token-storage.service";
import {DealService} from "../../services/deal/deal.service";

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

  constructor(private dealService: DealService,
              private tokenStorage: TokenStorageService
  ) {
    this.brokerId = tokenStorage.getUser().id;
    this.sort = ['desc', 'dealNumber'];
  }

  ngOnInit() {
    this.retrieveDeals();
  }

  sortTable(columnName: string) {
    this.sort = ['asc', columnName];
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
