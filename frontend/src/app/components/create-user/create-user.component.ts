import {Component, OnInit} from '@angular/core';
import {AuthService} from 'src/app/services/auth/auth.service';
import {StockExchange} from "../../model/StockExchange";
import {ExchangeService} from "../../services/exchange/exchange.service";

@Component({
  selector: 'app-create-user',
  templateUrl: './create-user.component.html',
  styleUrls: ['./create-user.component.css']
})
export class CreateUserComponent implements OnInit {
  form: any = {};
  exchanges: StockExchange[];

  isSuccessful = false;
  isSignUpFailed = false;
  errorMessage = '';
  isBroker = false;

  constructor(private authService: AuthService,
              private exchangeService: ExchangeService) {
    this.form.role = 'client';
  }

  ngOnInit() {
  }

  onSubmit() {
    this.authService.register(this.form).subscribe(
      data => {
        console.log(data);
        this.isSuccessful = true;
        this.isSignUpFailed = false;
      },
      err => {
        this.errorMessage = err.error.message;
        this.isSignUpFailed = true;
      }
    );
  }

  isBrokerRole() {
    this.isBroker = false;
  }

  showExchange() {
    this.isBroker = true;
    this.exchangeService.getAll().subscribe(
      data => {
        this.exchanges = data;
      },
      err => {
        this.errorMessage = err.error.message;
        this.isSignUpFailed = true;
      }
    );
  }
}
