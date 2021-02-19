import {Component, OnInit} from '@angular/core';
import {User} from 'src/app/model/user';
import {UserService} from 'src/app/services/user.service';

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.css']
})
export class UserListComponent implements OnInit {

  private users: User[];

  page = 1;
  count = 0;
  pageSize = 3;
  pageSizes = [3, 6, 9];
  sort: string[];
  sortMap: Map<string, string>;

  constructor(private userService: UserService) {
    this.sort = ['asc', 'id'];
    this.sortMap = new Map();
  }


  ngOnInit() {
    this.retrieveUsers();
  }

  retrieveUsers() {
    this.userService.getAll(this.page - 1, this.pageSize, this.sort)
      .subscribe(
        response => {
          const {content, totalElements} = response;
          this.users = content;
          this.count = totalElements;
        },
        error => {
          console.log(error);
        });

  }

  handlePageChange(event) {
    this.page = event;
    this.retrieveUsers();
  }

  handlePageSizeChange(event) {
    this.pageSize = event.target.value;
    this.page = 1;
    this.retrieveUsers();
  }

  sortTable(columnName: string) {
    if (this.sortMap.has(columnName)) {
      if (this.sortMap.get(columnName) === 'desc') {
        this.sortMap.delete(columnName);
      } else {
        this.sortMap.set(columnName, 'desc');
      }
    } else {
      this.sortMap.set(columnName, 'asc');
    }

    this.sort = [];
    let index = 0;
    for (const [key, value] of this.sortMap) {
      this.sort[index++] = value;
      this.sort[index++] = key;
    }
    this.retrieveUsers();
  }
}
