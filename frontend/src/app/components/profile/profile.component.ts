import { Component, OnInit } from '@angular/core';

import { TokenStorageService } from '../../services/token-storage/token-storage.service';
import {User} from "../../model/user";

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
  user: User;

  constructor(private token: TokenStorageService) { }

  ngOnInit() {
    this.user = this.token.getUser();
  }
}
