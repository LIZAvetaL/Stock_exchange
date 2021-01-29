import {Component, OnInit} from '@angular/core';
import {BrokerService} from 'src/app/services/broker/broker.service';
import {TokenStorageService} from "../../services/token-storage/token-storage.service";
import {UnemployedBroker} from "../../model/unemployed-broker";

@Component({
  selector: 'app-unemployed-broker-list',
  templateUrl: './unemployed-broker-list.component.html',
  styleUrls: ['./unemployed-broker-list.component.css']
})
export class UnemployedBrokerListComponent implements OnInit {
  clientId: number;
  brokers: UnemployedBroker[];
  title = '';

  page = 1;
  count = 0;
  pageSize = 3;
  pageSizes = [3, 6, 9];
  sort = 'id';

  isEmployed: boolean;

  constructor(private brokerService: BrokerService,
              private tokenService: TokenStorageService) {
    this.clientId = tokenService.getUser().id;
    this.isEmployed = false;
  }

  ngOnInit() {
    this.retrieveBrokers();
  }

  retrieveBrokers() {
    this.brokerService.getAll(this.title, this.page - 1, this.pageSize, this.sort)
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
    this.retrieveBrokers();
  }

  handlePageSizeChange(event) {
    this.pageSize = event.target.value;
    this.page = 1;
    this.retrieveBrokers();
  }

  employ(brokerId: number) {
    this.brokerService.employ(brokerId, this.clientId);
    this.isEmployed = true;
  }
}
