import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Exchange } from 'src/app/model/exchange';
import { ExchangeService } from 'src/app/services/exchange/exchange.service';

@Component({
  selector: 'app-exchange-details',
  templateUrl: './exchange-details.component.html',
  styleUrls: ['./exchange-details.component.css']
})
export class ExchangeDetailsComponent implements OnInit {

  private currentExchange: Exchange;

  constructor(private route: ActivatedRoute, 
    private exchangeService : ExchangeService) { }

  ngOnInit() {
    this.getExchange(this.route.snapshot.params.id);
  }
  getExchange(id: number) {
    this.exchangeService.get(id)
      .subscribe(
        data => {
          this.currentExchange = data;
          console.log(data);
        })
  }

}
