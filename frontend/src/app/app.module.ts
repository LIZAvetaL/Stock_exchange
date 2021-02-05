import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppRoutingModule} from './app-routing.module';
import {FormsModule} from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';
import {NgxPaginationModule} from 'ngx-pagination';

import {AppComponent} from './app.component';
import {LoginComponent} from './components/login/login.component';
import {RegisterComponent} from './components/register/register.component';
import {ProfileComponent} from './components/profile/profile.component';
import {authInterceptorProviders} from './helpers/auth.interceptor';
import {ExchangeListComponent} from './components/exchange-list/exchange-list.component';
import {UserListComponent} from './components/user-list/user-list.component';
import {CreateUserComponent} from './components/create-user/create-user.component';
import {UserDetailsComponent} from './components/user-details/user-details.component';
import {UnemployedBrokerListComponent} from './components/unemployed-broker-list/unemployed-broker-list.component';
import {ClientBidListComponent} from './components/client-bid-list/client-bid-list.component';
import {CreateBidComponent} from './components/create-bid/create-bid.component';
import {ExchangeDetailsComponent} from './components/exchange-details/exchange-details.component';
import {BidDetailsComponent} from "./components/bid-details/bid-details.component";
import {BrokersListComponent} from "./components/brokers-list/brokers-list.component";
import {CreateExchangeComponent} from "./components/create-exchange/create-exchange.component";
import {CreateDealComponent} from "./components/create-deal/create-deal.component";
import {BrokerBidListComponent} from "./components/broker-bid-list/broker-bid-list.component";


@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    UserListComponent,
    ProfileComponent,
    ExchangeListComponent,
    CreateUserComponent,
    UserDetailsComponent,
    UnemployedBrokerListComponent,
    ClientBidListComponent,
    CreateBidComponent,
    ExchangeDetailsComponent,
    BidDetailsComponent,
    BrokersListComponent,
    CreateExchangeComponent,
    CreateDealComponent,
    BrokerBidListComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    FormsModule,
    NgxPaginationModule
  ],
  providers: [authInterceptorProviders],
  bootstrap: [AppComponent]
})
export class AppModule {
}
