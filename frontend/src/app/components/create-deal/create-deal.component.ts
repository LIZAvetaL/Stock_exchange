import {Component, OnInit} from '@angular/core';
import {BidService} from "../../services/bid/bid.service";
import {TokenStorageService} from "../../services/token-storage/token-storage.service";
import {BrokerBid} from "../../model/broker-bid";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-create-deal',
  templateUrl: './create-deal.component.html',
  styleUrls: ['./create-deal.component.css']
})
export class CreateDealComponent implements OnInit {

  bids: BrokerBid[];
  bidId: number;

  page = 1;
  count = 0;
  pageSize = 3;
  pageSizes = [3, 6, 9];
  sort: string[];

  constructor(private bidService: BidService,
              private router: ActivatedRoute) {
    this.bidId = this.router.snapshot.params.id;
    this.sort = ['desc', 'bidNumber'];
  }

  ngOnInit() {
    this.retrieveBrokers();
  }

  retrieveBrokers() {
    this.bidService.getBidsForCreateDeal(this.page - 1, this.pageSize, this.sort, this.bidId)
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
    this.sort = ['asc', columnName];
    this.retrieveBrokers();
  }

  createDeal(sellerBidId: number) {
    this.bidService.createDeal(this.bidId, sellerBidId).subscribe();
  }
}
