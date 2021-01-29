import { Component, OnInit } from '@angular/core';
import { StockExchange } from 'src/app/model/StockExchange';
import { ExchangeService } from 'src/app/services/exchange/exchange.service';
import { TokenStorageService } from 'src/app/services/token-storage/token-storage.service';

@Component({
  selector: 'app-exchange-list',
  templateUrl: './exchange-list.component.html',
  styleUrls: ['./exchange-list.component.css']
})
export class ExchangeListComponent implements OnInit {

  private exchanges: StockExchange[];
  private ownerId: number;

  constructor(
    private ecxhangeService: ExchangeService,
    private tokenStorage:TokenStorageService
    ) {
    this.ownerId = tokenStorage.getUser().id;
  }

  ngOnInit() {
    this.ecxhangeService.getExchange(this.ownerId).subscribe(data => {
      this.exchanges = data;
    })
  }
  onSubmit() {

  }

}
