import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { User } from 'src/app/model/user';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-user-details',
  templateUrl: './user-details.component.html',
  styleUrls: ['./user-details.component.css']
})
export class UserDetailsComponent implements OnInit {

  private currentUser:User = {
    id:0,
    name: '',
    email: '',
    role:''
  };

  constructor(private route: ActivatedRoute,
    private userService: UserService) { }

  ngOnInit() {
    this.userService.get(this.route.snapshot.params.id)
      .subscribe(
        data => {
          this.currentUser = data;
          console.log(data);
        })
  }
}


