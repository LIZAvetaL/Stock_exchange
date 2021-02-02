import {Component, OnInit} from '@angular/core';
import {Broker} from '../../model/broker';
import {ActivatedRoute, Router} from '@angular/router';
import {TokenStorageService} from '../../services/token-storage/token-storage.service';
import {BidService} from '../../services/bid/bid.service';
import {BrokerService} from '../../services/broker/broker.service';
import {Bid} from '../../model/bid';

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

  isEdit: boolean;

  constructor(private tokenService: TokenStorageService,
              private bidService: BidService,
              private brokerService: BrokerService,
              private route: ActivatedRoute) {
    this.bidId = this.route.snapshot.params.id;
    this.clientId = tokenService.getUser().id;
    this.editBid = new Bid();
    this.isEdit = false;
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

  save() {
    this.bidService.update(this.editBid).subscribe();
    this.isEdit = false;
  }

  edit() {
    this.isEdit = true;
  }
}
