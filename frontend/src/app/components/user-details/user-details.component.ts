import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {User} from 'src/app/model/user';
import {UserService} from 'src/app/services/user.service';
import {MessageResponse} from '../../model/messageResponse';
import {error} from "util";
import {catchError} from "rxjs/operators";
import {throwError} from "rxjs";
import {StockExchange} from "../../model/StockExchange";
import {ExchangeService} from "../../services/exchange/exchange.service";
import {BrokerService} from "../../services/broker/broker.service";

@Component({
  selector: 'app-user-details',
  templateUrl: './user-details.component.html',
  styleUrls: ['./user-details.component.css']
})
export class UserDetailsComponent implements OnInit {

  user: User;
  response: MessageResponse;
  changeRole: boolean;

  isBroker = false;
  exchanges: StockExchange[];
  exchange: string;
  isSignUpFailed = false;

  constructor(private route: ActivatedRoute,
              private userService: UserService,
              private exchangeService: ExchangeService,
              private brokerService: BrokerService) {
    this.changeRole = true;
    this.user = new User();
    this.response = new MessageResponse();
  }

  ngOnInit() {
    this.userService.get(this.route.snapshot.params.id)
      .subscribe(
        data => {
          this.user = data;
          console.log(data);
        },
        err => {
          console.log(err.error);
          console.log(err);
        });

  }

  onSubmit() {
    if (this.user.role === 'ROLE_BROKER') {
      console.log(this.exchange);
      this.brokerService.update(this.user.id, this.exchange).subscribe(
        data => {
          this.response = data;
          this.changeRole = true;
        },
        err => {
          this.response = err.error.message;
          this.isSignUpFailed = true;
        }
      );
    } else {
      this.userService.update(this.user.id, this.user.role).subscribe(
        data => {
          this.response = data;
          this.changeRole = true;
        },
        err => {
          this.response = err.error.message;
          this.isSignUpFailed = true;
        }
      );
    }
  }

  isBrokerRole() {
    if (this.user.role === 'ROLE_BROKER') {
      this.showExchange();
    } else {
      this.isBroker = false;
    }
  }

  showExchange() {
    this.isBroker = true;
    this.exchangeService.getAll().subscribe(
      data => {
        this.exchanges = data;
      },
      err => {
        this.response = err.error.message;
        this.isSignUpFailed = true;
      }
    );
  }

  isChanged() {
    this.changeRole = false;
    this.response.message = '';
  }

  isBlock() {
    return this.user.status !== 'Unblock';
  }

  unblockUser() {
    this.userService.unblockUser(this.user.id).subscribe(
      data => {
        this.response = data;
        this.user.status = 'Unblock';
      },
      err => {
        this.response.message = err.error.message;
      }
    );
  }

  blockUser() {
    this.userService.blockUser(this.user.id).subscribe(
      data => {
        this.response = data;
        this.user.status = 'Block';
      },
      err => {
        this.response.message = err.error.message;
      }
    );
  }
}


