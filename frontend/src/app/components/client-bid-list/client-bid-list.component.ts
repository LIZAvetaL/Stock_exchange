import { Component, OnInit } from '@angular/core';
import { Bid } from 'src/app/model/bid';
import { ClientService } from 'src/app/services/client/client.service';
import { TokenStorageService } from 'src/app/services/token-storage/token-storage.service';

@Component({
  selector: 'app-bid-list',
  templateUrl: './client-bid-list.component.html',
  styleUrls: ['./client-bid-list.component.css']
})
export class ClientBidListComponent implements OnInit {

  bids: Bid[];
  clientId: number;

  page = 1;
  count = 0;
  pageSize = 3;
  pageSizes = [3, 6, 9];

  constructor(private clientService: ClientService,
    private tokenStorage: TokenStorageService
  ) {
    this.clientId = tokenStorage.getUser().id;
  }

  ngOnInit() {
    this.retrieveBrokers()
  }
  retrieveBrokers() {
    this.clientService.getAllBids(this.page - 1, this.pageSize, this.clientId)
      .subscribe(
        response => {
          const { content, totalElements } = response;
          this.bids = content;
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
}
