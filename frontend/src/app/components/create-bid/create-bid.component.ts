import { Component, OnInit } from '@angular/core';
import { Broker } from 'src/app/model/broker';
import { BrokerService } from 'src/app/services/broker/broker.service';
import { ClientService } from 'src/app/services/client/client.service';
import { TokenStorageService } from 'src/app/services/token-storage/token-storage.service';

@Component({
  selector: 'app-create-bid',
  templateUrl: './create-bid.component.html',
  styleUrls: ['./create-bid.component.css']
})
export class CreateBidComponent implements OnInit {
  form: any = {};
  brokers:Broker[];
  clientId: number;
  constructor(private tokenService: TokenStorageService,
    private clientService:ClientService,
    private brokerService:BrokerService) { 
      this.clientId=tokenService.getUser().id;
    }

  ngOnInit() {
    this.brokerService.getClientsBrokers(this.clientId).subscribe(data => {
      this.brokers = data;
    })
  }

}
