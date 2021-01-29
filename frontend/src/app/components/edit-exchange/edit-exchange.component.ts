import {Component, OnInit} from '@angular/core';
import {StockExchange} from '../../model/StockExchange';
import {MessageResponse} from '../../model/messageResponse';
import {ActivatedRoute} from '@angular/router';
import {ExchangeService} from '../../services/exchange/exchange.service';

@Component({
  selector: 'app-edit-exchange',
  templateUrl: './edit-exchange.component.html',
  styleUrls: ['./edit-exchange.component.css']
})
export class EditExchangeComponent implements OnInit {
  exchange: StockExchange;
  response: MessageResponse;

  constructor(private route: ActivatedRoute,
              private exchangeService: ExchangeService) {
    this.exchange = new StockExchange();
  }

  ngOnInit() {
    this.getExchange(this.route.snapshot.params.id);
  }

  getExchange(id: number) {
    this.exchangeService.get(id)
      .subscribe(
        data => {
          this.exchange = data;
        });
  }

  onSubmit() {
    this.exchangeService.updateExchange(this.exchange);
  }

}
