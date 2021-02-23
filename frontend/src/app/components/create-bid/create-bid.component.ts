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
  isOk: boolean;
  isFail: boolean;

  messageAmount = '';
  messageMinPrice = '';
  messageMaxPrice = '';
  messageDate = '';

  constructor(private tokenService: TokenStorageService,
              private bidService: BidService,
              private brokerService: BrokerService) {
    this.clientId = tokenService.getUser().id;
    this.createBid = new CreateBid();
    this.createBid.amount = 1;
    this.createBid.minPrice = 1;
    this.createBid.maxPrice = 2;
    this.createBid.dueDate = '2021-02-25';
    this.response = new MessageResponse();
    this.isFail = false;
    this.isOk = false;
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
        this.isOk = true;
        this.isFail = false;
      },
      error => {
        this.response.message = error.error;
        this.isOk = false;
        this.isFail = true;
      });
  }

  onSubmit(form: NgForm) {
    if (form.invalid || this.isAmount() || this.isMinPrice() || this.isMaxPrice() || this.isDueDate()) {

      this.isFail = true;
    } else {
      this.create();
    }
  }

  isAmount() {
    if (this.createBid.amount <= 0) {
      if (this.createBid.amount === null) {
        this.messageAmount = 'Amount is required';
      } else {
        this.messageAmount = 'Amount must be more than 0';
      }
      return true;
    } else {
      return false;
    }
  }

  isMinPrice() {
    if (this.createBid.minPrice <= 0) {
      if (this.createBid.minPrice === null) {
        this.messageMinPrice = 'Min Price is required';
      } else {
        this.messageMinPrice = 'Min Price must be more than 0';
      }
      return true;
    } else {
      return false;
    }
  }

  isMaxPrice() {
    if (this.isMinPrice() || (this.createBid.maxPrice <= this.createBid.minPrice)) {
      if (this.createBid.maxPrice === null) {
        this.messageMaxPrice = 'Max Price is required';
      } else {
        this.messageMaxPrice = 'Max Price must be more than Min Price';
      }
      return true;
    } else {
      return false;
    }
  }


  isDueDate() {
    if (this.createBid.dueDate < '2021-02-19') {
      if (this.createBid.dueDate === '') {
        this.messageDate = 'Due Date is required';
      } else {
        this.messageDate = 'Due Date must be more than Current Date';
      }
      return true;
    } else {
      return false;
    }
  }
}
