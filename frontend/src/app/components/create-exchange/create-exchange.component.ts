import {Component, OnInit} from '@angular/core';
import {StockExchange} from "../../model/StockExchange";
import {MessageResponse} from "../../model/messageResponse";
import {ActivatedRoute, Router, RouterLinkActive} from "@angular/router";
import {ExchangeService} from "../../services/exchange/exchange.service";
import {TokenStorageService} from "../../services/token-storage/token-storage.service";

@Component({
  selector: 'app-create-exchange',
  templateUrl: './create-exchange.component.html',
  styleUrls: ['./create-exchange.component.css']
})
export class CreateExchangeComponent implements OnInit {

  exchange: StockExchange;
  ownerId: number;

  constructor(private route: Router,
              private exchangeService: ExchangeService,
              private tokenService: TokenStorageService) {
    this.exchange = new StockExchange();
    this.ownerId = tokenService.getUser().id;
  }

  Save() {
    this.exchange.creationDate = Date.now();
    this.exchangeService.saveExchange(this.exchange, this.ownerId).subscribe();
    this.route.navigate(['/owner/exchanges']).then(r => console.log('oops'));
  }

  ngOnInit() {
  }

}
