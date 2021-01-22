import { Component, OnInit } from '@angular/core';
import { Broker } from 'src/app/model/broker';
import { BrokerService } from 'src/app/services/broker/broker.service';

@Component({
  selector: 'app-unemployed-broker-list',
  templateUrl: './unemployed-broker-list.component.html',
  styleUrls: ['./unemployed-broker-list.component.css']
})
export class UnemployedBrokerListComponent implements OnInit {

  brokers: Broker[];
  title = '';

  page = 1;
  count = 0;
  pageSize = 3;
  pageSizes = [3, 6, 9];
  sort = "id";

  constructor(private brokerService: BrokerService) { }

  ngOnInit() {
    this.retrieveBrokers()
  }

  getRequestParams(searchTitle: string, page: number, pageSize: number, sort: string) {
    let params = {};

    if (searchTitle) {
      params[`title`] = searchTitle;
    }

    if (page) {
      params[`page`] = page - 1;
    }

    if (pageSize) {
      params[`size`] = pageSize;
    }

    if (sort) {
      params[`sort`] = sort;
    }

    return params;
  }

  retrieveBrokers() {
    this.brokerService.getAll(this.title, this.page - 1, this.pageSize, this.sort)
      .subscribe(
        response => {
          const { brokers, totalItems } = response;
          this.brokers = brokers;
          this.count = totalItems;
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

}
