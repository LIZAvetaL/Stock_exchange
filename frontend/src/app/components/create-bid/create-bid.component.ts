import {Component, OnInit} from '@angular/core';
import {Broker} from 'src/app/model/broker';
import {BrokerService} from 'src/app/services/broker/broker.service';
import {TokenStorageService} from 'src/app/services/token-storage/token-storage.service';
import {CreateBid} from '../../model/create-bid';
import {BidService} from '../../services/bid/bid.service';
import {Router} from '@angular/router';
import {MessageResponse} from '../../model/messageResponse';
import {NgForm} from '@angular/forms';
import {DatePipe} from '@angular/common';

@Component({
  selector: 'app-create-bid',
  templateUrl: './create-bid.component.html',
  styleUrls: ['./create-bid.component.css']
})
export class CreateBidComponent implements OnInit {
  createBid: CreateBid;
  brokers: Broker[];
  clientId: number;

  response: MessageResponse;
  datePipe: DatePipe;

  constructor(private tokenService: TokenStorageService,
              private bidService: BidService,
              private brokerService: BrokerService) {
    this.clientId = tokenService.getUser().id;
    this.createBid = new CreateBid();
    this.response = new MessageResponse();
  }

  ngOnInit() {
    this.brokerService.getBrokers(this.clientId).subscribe(data => {
      this.brokers = data;
    });
  }

  create() {
    this.bidService.create(this.clientId, this.createBid).subscribe(
      data => {
        this.response = data;
      },
      error => {
        this.response.message = error.error;
      });
  }

  onSubmit(form: NgForm) {
    if (form.invalid || this.isAmount() || this.isMinPrice() || this.isMaxPrice() || this.isDueDate()) {
      this.response.message = 'Check input data';
    } else {
      this.create();
    }
  }

  isAmount() {
    return (this.createBid.amount <= 0);
  }

  isMinPrice() {
    return (this.createBid.minPrice <= 0);
  }

  isMaxPrice() {
    return this.isMinPrice() || (this.createBid.maxPrice <= this.createBid.minPrice);
  }

  isDueDate() {
    return this.createBid.dueDate < '2021-02-19';
  }
}
