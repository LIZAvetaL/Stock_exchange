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

  constructor(private route: ActivatedRoute,
              private exchangeService: ExchangeService) {
    this.exchange = new StockExchange();
    this.isEdit = false;
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

  onSubmit() {
    this.exchangeService.changeStatus(this.exchange.id, this.status)
      .subscribe(
        data => {
          this.response = data;
        }
      );
  }

  edit() {
    this.isEdit = true;
  }

  Save() {
    this.exchangeService.updateExchange(this.exchange).subscribe(
      data => {
        this.response = data;
        this.isEdit = false;
      }
    );
  }
}
