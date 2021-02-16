import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {StockExchange} from 'src/app/model/StockExchange';
import {ExchangeService} from 'src/app/services/exchange/exchange.service';
import {MessageResponse} from '../../model/messageResponse';

@Component({
  selector: 'app-exchange-details',
  templateUrl: './exchange-details.component.html',
  styleUrls: ['./exchange-details.component.css']
})
export class ExchangeDetailsComponent implements OnInit {
  status: string;
  exchange: StockExchange;
  response: MessageResponse;

  isEdit: boolean;
  showMessage: boolean;

  constructor(private route: ActivatedRoute,
              private exchangeService: ExchangeService) {
    this.exchange = new StockExchange();
    this.isEdit = false;
    this.showMessage = false;
    this.response = new MessageResponse();
  }

  ngOnInit() {
    this.getExchange(this.route.snapshot.params.id);
  }

  getExchange(id: number) {
    this.exchangeService.get(id)
      .subscribe(
        data => {
          this.exchange = data;
          this.status = this.exchange.status;
        });
  }

  edit() {
    this.isEdit = true;
    this.showMessage = false;
  }

  Save() {
    this.exchangeService.updateExchange(this.exchange).subscribe(
      data => {
        this.response = data;
        this.isEdit = false;
        this.showMessage = true;
      }
    );
  }
}
