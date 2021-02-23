import {Component, OnInit} from '@angular/core';
import {StockExchange} from "../../model/StockExchange";
import {MessageResponse} from "../../model/messageResponse";
import {ActivatedRoute, Router, RouterLinkActive} from "@angular/router";
import {ExchangeService} from "../../services/exchange/exchange.service";
import {TokenStorageService} from "../../services/token-storage/token-storage.service";
import {NgForm} from "@angular/forms";
import {CreateExchange} from "../../model/create-exchange";

@Component({
  selector: 'app-create-exchange',
  templateUrl: './create-exchange.component.html',
  styleUrls: ['./create-exchange.component.css']
})
export class CreateExchangeComponent implements OnInit {

  exchange: CreateExchange;
  ownerId: number;

  response: MessageResponse;
  isSuccessful: boolean;
  isFailed: boolean;

  constructor(private route: Router,
              private exchangeService: ExchangeService,
              private tokenService: TokenStorageService) {
    this.exchange = new StockExchange();
    this.ownerId = tokenService.getUser().id;
    this.response = new MessageResponse();
    this.isSuccessful = false;
    this.isFailed = false;
  }

  Save(form: NgForm) {
    if (form.valid) {
      this.exchangeService.saveExchange(this.exchange, this.ownerId).subscribe(
        data => {
          this.isFailed = false;
          this.isSuccessful = true;
          this.response.message = data.message;
        }, error => {
          this.response = error.error;
          this.isFailed = true;
          this.isSuccessful = false;
        }
      );
    }
  }

  ngOnInit() {
  }

}
