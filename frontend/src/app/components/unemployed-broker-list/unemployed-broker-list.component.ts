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
  sort: string[];
  sortMap: Map<string, string>;
  currentItem: number;

  constructor(private brokerService: BrokerService,
              private tokenService: TokenStorageService) {
    this.clientId = tokenService.getUser().id;
    this.sort = [];
    this.sortMap = new Map();
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
          this.currentItem = this.page * this.pageSize;
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

  employ(broker: UnemployedBroker) {
    this.brokerService.employ(broker.id, this.clientId).subscribe();
    broker.isEmploy = true;
  }

  dismiss(broker: UnemployedBroker) {
    this.brokerService.dismiss(broker.id).subscribe();
    broker.isEmploy = false;
  }

  sortTable(columnName: string) {
    if (this.sortMap.has(columnName)) {
      if (this.sortMap.get(columnName) === 'desc') {
        this.sortMap.delete(columnName);
      } else {
        this.sortMap.set(columnName, 'desc');
      }
    } else {
      this.sortMap.set(columnName, 'asc');
    }

    this.sort = [];
    let index = 0;
    for (const [key, value] of this.sortMap) {
      this.sort[index++] = value;
      this.sort[index++] = key;
    }
    this.retrieveBrokers();
  }

}
