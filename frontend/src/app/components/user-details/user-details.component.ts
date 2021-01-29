import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {User} from 'src/app/model/user';
import {UserService} from 'src/app/services/user.service';
import {MessageResponse} from '../../model/messageResponse';

@Component({
  selector: 'app-user-details',
  templateUrl: './user-details.component.html',
  styleUrls: ['./user-details.component.css']
})
export class UserDetailsComponent implements OnInit {

  user: User;
  response: MessageResponse;
  changeRole: boolean;

  constructor(private route: ActivatedRoute,
              private userService: UserService) {
    this.changeRole = true;
  }

  ngOnInit() {
    this.userService.get(this.route.snapshot.params.id)
      .subscribe(
        data => {
          this.user = data;
          console.log(data);
        });
  }

  onSubmit() {
    this.userService.update(this.user.id, this.user.role).subscribe(
      data => {
        this.response = data;
        this.changeRole = true;
      }
    );
  }
}


