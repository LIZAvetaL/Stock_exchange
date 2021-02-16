import {Component, OnInit} from '@angular/core';
import {UnemployedBroker} from "../../model/unemployed-broker";
import {BrokerService} from "../../services/broker/broker.service";
import {TokenStorageService} from "../../services/token-storage/token-storage.service";
import {Broker} from "../../model/broker";
import {BrokerStatistics} from "../../model/BrokerStatistics";

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

  page = 1;
  count = 0;
  pageSize = 3;
  pageSizes = [3, 6, 9];


  constructor(private brokerService: BrokerService,
              private tokenService: TokenStorageService) {
    this.clientId = tokenService.getUser().id;
  }

  ngOnInit() {
    this.getBrokerStatistics();
    this.retrieveBrokers();
  }

  getBrokerStatistics() {
    this.brokerService.getStatistics(this.page - 1, this.pageSize, this.clientId).subscribe(
      response => {
        const {content, totalElements} = response;
        this.statistics = content;
        this.count = totalElements;
        console.log(response);
      },
      error => {
        console.log(error);
      });
  }

  retrieveBrokers() {
    this.brokerService.getClientsBrokers(this.page - 1, this.pageSize, this.clientId)
      .subscribe(
        response => {
          const {content, totalElements} = response;
          this.brokers = content;
          this.count = totalElements;
          console.log(response);
        },
        error => {
          console.log(error);
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

  employ(broker: UnemployedBroker) {
    this.brokerService.employ(broker.id, this.clientId).subscribe();
    broker.isEmploy = false;
  }

  dismiss(broker: UnemployedBroker) {
    this.brokerService.dismiss(broker.id).subscribe();
    broker.isEmploy = true;
  }

}
