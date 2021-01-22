import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { RegisterComponent } from './components/register/register.component';
import { LoginComponent } from './components/login/login.component';
import { ProfileComponent } from './components/profile/profile.component';
import { ExchangeListComponent } from './components/exchange-list/exchange-list.component';
import { UserListComponent } from './components/user-list/user-list.component';
import { CreateUserComponent } from './components/create-user/create-user.component';
import { UserDetailsComponent } from './components/user-details/user-details.component';
import { UnemployedBrokerListComponent } from './components/unemployed-broker-list/unemployed-broker-list.component';

const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'profile', component: ProfileComponent },
  { path: 'owner/exchanges', component: ExchangeListComponent },
  { path: 'admin/users', component: UserListComponent },
  { path: 'admin/user/create', component: CreateUserComponent },
  { path: 'user-details/:id', component: UserDetailsComponent },
  { path: 'client/unemployed-brokers', component: UnemployedBrokerListComponent },
  { path: '', redirectTo: 'login', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
