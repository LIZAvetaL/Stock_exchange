import {Component, OnInit} from '@angular/core';
import {BidService} from "../../services/bid/bid.service";
import {TokenStorageService} from "../../services/token-storage/token-storage.service";
import {BrokerBid} from "../../model/broker-bid";
import {ActivatedRoute, Router} from "@angular/router";
import {Bid} from "../../model/bid";

@Component({
  selector: 'app-create-deal',
  templateUrl: './create-deal.component.html',
  styleUrls: ['./create-deal.component.css']
})
export class CreateDealComponent implements OnInit {

  bids: BrokerBid[];
  bid: Bid;

  page = 1;
  count = 0;
  pageSize = 3;
  pageSizes = [3, 6, 9];
  sort: string[];
  userId: number;

  constructor(private bidService: BidService,
              private router: ActivatedRoute,
              private route: Router,
              private tokenService: TokenStorageService) {
    this.bid = new Bid();
    this.bid.id = this.router.snapshot.params.id;
    this.sort = ['desc', 'bidNumber'];
    this.userId = tokenService.getUser().id;
  }

  ngOnInit() {
    this.retrieveBrokers();
    this.bidService.getBid(this.bid.id).subscribe(
      data => {
        this.bid = data;
      },
      error => {
        console.log(error);
      }
    );
  }

  retrieveBrokers() {
    this.bidService.getBidsForCreateDeal(this.page - 1, this.pageSize, this.sort, this.bid.id)
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

  createDeal(sellerBidId: number, price: number) {
    this.bidService.createDeal(this.bid.id, sellerBidId, this.bid, price).subscribe(
      data => {
        this.route.navigate(['broker/deals', data]);
      }
    );
  }
}
