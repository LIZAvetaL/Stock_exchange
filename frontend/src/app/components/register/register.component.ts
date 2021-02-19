import {Component, OnInit} from '@angular/core';
import {AuthService} from '../../services/auth/auth.service';
import {ExchangeService} from "../../services/exchange/exchange.service";
import {StockExchange} from "../../model/StockExchange";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  form: any = {};
  exchanges: StockExchange[];

  isSuccessful = false;
  isSignUpFailed = false;
  errorMessage = '';
  isBroker = false;

  constructor(private authService: AuthService,
              private exchangeService: ExchangeService) {
    this.form.role = 'ROLE_CLIENT';
  }

  ngOnInit() {
  }

  onSubmit() {
    console.log(this.form);
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
