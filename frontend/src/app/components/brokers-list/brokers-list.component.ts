import {Component, OnInit} from '@angular/core';
import {UnemployedBroker} from "../../model/unemployed-broker";
import {BrokerService} from "../../services/broker/broker.service";
import {TokenStorageService} from "../../services/token-storage/token-storage.service";
import {Broker} from "../../model/broker";
import {BrokerStatistics} from "../../model/BrokerStatistics";
import {MessageResponse} from "../../model/messageResponse";

@Component({
  selector: 'app-brokers-list',
  templateUrl: './brokers-list.component.html',
  styleUrls: ['./brokers-list.component.css']
})
export class BrokersListComponent implements OnInit {

  clientId: number;
  title = '';
  statistics: BrokerStatistics[];
  brokers: Broker[];
  response: MessageResponse;

  page = 1;
  count = 0;
  pageSize = 3;
  pageSizes = [3, 6, 9];
  isOperationFailed: boolean;
  currentItem: number;


  constructor(private brokerService: BrokerService,
              private tokenService: TokenStorageService) {
    this.clientId = tokenService.getUser().id;
    this.response = new MessageResponse();
    this.isOperationFailed = false;
  }

  ngOnInit() {
    this.getBrokerStatistics();
  }

  getBrokerStatistics() {
    this.brokerService.getStatistics(this.page - 1, this.pageSize, this.clientId).subscribe(
      response => {
        const {content, totalElements} = response;
        this.statistics = content;
        this.count = totalElements;
        this.currentItem = this.page * this.pageSize;
        console.log(response);
      },
      error => {
        this.response.message = error.error.message;
        this.isOperationFailed = true;
      });
  }

  handlePageChange(event) {
    this.page = event;
    this.getBrokerStatistics();
  }

  handlePageSizeChange(event) {
    this.pageSize = event.target.value;
    this.page = 1;
    this.getBrokerStatistics();
  }

  employ(broker: Broker) {
    this.brokerService.employ(broker.id, this.clientId).subscribe();
    broker.isEmploy = false;
    this.isOperationFailed = false;
  }

  dismiss(broker: Broker) {
    this.brokerService.dismiss(broker.id).subscribe(
      data => {
        this.response = data;
        broker.isEmploy = true;
        this.isOperationFailed = false;
      }, error => {
        this.isOperationFailed = true;
        console.log(error);
        this.response.message = error.error.message;
      }
    );
  }

}
