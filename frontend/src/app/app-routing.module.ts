import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';

import {RegisterComponent} from './components/register/register.component';
import {LoginComponent} from './components/login/login.component';
import {ProfileComponent} from './components/profile/profile.component';
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


const routes: Routes = [
  {path: 'login', component: LoginComponent},
  {path: 'register', component: RegisterComponent},
  {path: 'profile', component: ProfileComponent},
  {path: 'owner/exchanges', component: ExchangeListComponent},
  {path: 'owner/create-exchange', component: CreateExchangeComponent},
  {path: 'exchange-details/:id', component: ExchangeDetailsComponent},
  {path: 'admin/users', component: UserListComponent},
  {path: 'admin/user/create', component: CreateUserComponent},
  {path: 'user-details/:id', component: UserDetailsComponent},
  {path: 'broker/bids', component: BrokerBidListComponent},
  {path: 'broker/create-deal/:id', component: CreateDealComponent},
  {path: 'client/bids', component: ClientBidListComponent},
  {path: 'client/create-bid', component: CreateBidComponent},
  {path: 'client/bid-details/:id', component: BidDetailsComponent},
  {path: 'client/unemployed-brokers', component: UnemployedBrokerListComponent},
  {path: 'client/brokers', component: BrokersListComponent},
  {path: '', redirectTo: 'login', pathMatch: 'full'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
