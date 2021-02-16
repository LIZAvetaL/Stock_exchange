import {Component, OnInit} from '@angular/core';
import {Broker} from '../../model/broker';
import {ActivatedRoute, Router} from '@angular/router';
import {TokenStorageService} from '../../services/token-storage/token-storage.service';
import {BidService} from '../../services/bid/bid.service';
import {BrokerService} from '../../services/broker/broker.service';
import {Bid} from '../../model/bid';
import {NgForm} from "@angular/forms";
import {MessageResponse} from "../../model/messageResponse";

@Component({
  selector: 'app-bid-details',
  templateUrl: './bid-details.component.html',
  styleUrls: ['./bid-details.component.css']
})
export class BidDetailsComponent implements OnInit {

  editBid: Bid;
  brokers: Broker[];
  clientId: number;
  bidId: number;
  response: MessageResponse;

  isEdit: boolean;

  constructor(private tokenService: TokenStorageService,
              private bidService: BidService,
              private brokerService: BrokerService,
              private route: ActivatedRoute) {
    this.bidId = this.route.snapshot.params.id;
    this.clientId = tokenService.getUser().id;
    this.editBid = new Bid();
    this.isEdit = false;
    this.response = new MessageResponse();
  }

  ngOnInit() {
    this.brokerService.getBrokers(this.clientId).subscribe(data => {
      this.brokers = data;
      console.log(data);
    });
    this.bidService.getBid(this.bidId).subscribe(
      data => {
        this.editBid = data;
      }
    );
  }

  save(form: NgForm) {
    if (form.invalid || this.isAmount() || this.isMinPrice() || this.isMaxPrice()) {
      this.response.message = 'check data';
    } else {
      this.bidService.update(this.editBid).subscribe();
      this.isEdit = false;
    }
  }

  edit() {
    this.isEdit = true;
  }

  isAmount() {
    return (this.editBid.amount <= 0);
  }

  isMinPrice() {
    return (this.editBid.minPrice <= 0);
  }

  isMaxPrice() {
    return this.isMinPrice() || (this.editBid.maxPrice <= this.editBid.minPrice);
  }
}
