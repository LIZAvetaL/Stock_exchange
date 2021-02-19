import { Component, OnInit } from '@angular/core';
import { TokenStorageService } from './services/token-storage/token-storage.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  private role: string='';
  isLoggedIn = false;
  showAdminBoard = false;
  showOwnerBoard = false;
  showClientBoard = false;
  showBrokerBoard = false;
  email: string;

  constructor(private tokenStorageService: TokenStorageService) { }

  ngOnInit() {
    this.isLoggedIn = !!this.tokenStorageService.getToken();

    if (this.isLoggedIn) {
      const user = this.tokenStorageService.getUser();
      this.role = user.role;
        this.showAdminBoard = this.role.includes('ROLE_ADMIN');
      this.showOwnerBoard = this.role.includes('ROLE_OWNER');
      this.showClientBoard = this.role.includes('ROLE_CLIENT');
      this.showBrokerBoard = this.role.includes('ROLE_BROKER');
      this.email = user.email;
    }
  }

  logout() {
    this.tokenStorageService.signOut();
    window.location.reload();
  }
}
