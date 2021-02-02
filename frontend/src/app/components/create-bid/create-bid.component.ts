import {Component, OnInit} from '@angular/core';
import {Broker} from 'src/app/model/broker';
import {BrokerService} from 'src/app/services/broker/broker.service';
import {TokenStorageService} from 'src/app/services/token-storage/token-storage.service';
import {CreateBid} from '../../model/create-bid';
import {BidService} from '../../services/bid/bid.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-create-bid',
  templateUrl: './create-bid.component.html',
  styleUrls: ['./create-bid.component.css']
})
export class CreateBidComponent implements OnInit {
  createBid: CreateBid;
  brokers: Broker[];
  clientId: number;
  private router: Router;

  constructor(private tokenService: TokenStorageService,
              private bidService: BidService,
              private brokerService: BrokerService) {
    this.clientId = tokenService.getUser().id;
    this.createBid = new CreateBid();
  }

  ngOnInit() {
    this.brokerService.getBrokers(this.clientId).subscribe(data => {
      this.brokers = data;
      console.log(data);
    });
  }

  onSubmit() {
    this.bidService.create(this.clientId, this.createBid).subscribe();
  }
}
