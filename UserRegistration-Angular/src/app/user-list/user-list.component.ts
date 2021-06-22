import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { UserInfo } from '../model/user-info';
import { UserServiceService } from '../service/user-service.service';

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.css']
})
export class UserListComponent implements OnInit {
  usersList: UserInfo[] = [];
  tempUsersList: UserInfo[] = new Array();
  AllActiveUserList: UserInfo[] = new Array();
  UpUser: UserInfo = new UserInfo();
  isupdated = false;
  constructor(private userService: UserServiceService) { }

  ngOnInit(): void {
    this.isupdated = false;
    this.getAllActiveUser();

  }
 

  userFormUpdate = new FormGroup({
    firstName: new FormControl('', [Validators.required, Validators.minLength(5)]),
    lastName: new FormControl('', [Validators.required, Validators.minLength(5)]),
    pincode: new FormControl(''),
    dob: new FormControl(''),
    doj: new FormControl(''),
  });
  get firstName() { return this.userFormUpdate.get('firstName'); }
  get lastName() { return this.userFormUpdate.get('lastName'); }
  get pincode() { return this.userFormUpdate.get('pincode'); }
  get dob() { return this.userFormUpdate.get('dob'); }
  get doj() { return this.userFormUpdate.get('doj'); }

  getAllActiveUser() {
    this.userService.getAllUser().subscribe(data => {
      this.usersList = data;
      this.AllActiveUserList = data
    })

  }
  deleteMessage: boolean = false
  deleteUser(id: any, status: number) {
    this.userService.deleteUser(id, status).subscribe(data => {
      alert(data);
      this.deleteMessage = true
      this.getAllActiveUser();

    }, error => { console.log(error) })
  }
  upUserID: any
  updateUser(id: any, user: UserInfo) {
    this.isupdated = false
    this.upUserID = id;
    this.userFormUpdate.patchValue(user)

  }
  updateUserInfo() {

    this.UpUser = new UserInfo();
    this.UpUser.id = this.upUserID;
    this.UpUser.firstName = this.firstName?.value;
    this.UpUser.lastName = this.lastName?.value;
    this.UpUser.pincode = this.pincode?.value;
    this.UpUser.dob = this.dob?.value;
    this.UpUser.doj = this.doj?.value;

    this.userService.UpUser(this.UpUser)
      .subscribe(response => {
        if (response.message != "Success") {
          alert(response.message)
          return
        } else {
          this.isupdated = true;
          this.UpUser = new UserInfo();
          this.getAllActiveUser()
          console.log(response)
        }
      }, error => console.log(error));

    // this.userService.updateUser()

  }
  changeisUpdate() {
    this.isupdated = false
  }
 
}
