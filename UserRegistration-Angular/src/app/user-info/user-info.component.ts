import { formatDate } from '@angular/common';
import { THIS_EXPR } from '@angular/compiler/src/output/output_ast';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { UserInfo } from '../model/user-info';
import { UserServiceService } from '../service/user-service.service';

@Component({
  selector: 'app-user-info',
  templateUrl: './user-info.component.html',
  styleUrls: ['./user-info.component.css']
})
export class UserInfoComponent implements OnInit {

  constructor(private userService: UserServiceService) { }
  submitted: boolean = false
  newUser: UserInfo = new UserInfo();
  regID: number = 0
  name: String = ""
  ngOnInit(): void {
    this.submitted = false
  }

  userForm = new FormGroup({
    firstName: new FormControl('', [Validators.required, Validators.minLength(5)]),
    lastName: new FormControl('', [Validators.required, Validators.minLength(5)]),
    pincode: new FormControl(''),
    dob: new FormControl(''),
    doj: new FormControl(''),
  });
  get firstName() { return this.userForm.get('firstName'); }
  get lastName() { return this.userForm.get('lastName'); }
  get pincode() { return this.userForm.get('pincode'); }
  get dob() { return this.userForm.get('dob'); }
  get doj() { return this.userForm.get('doj'); }


  addUserForm() {
    this.submitted = false;
    this.userForm.reset();
  }

  saveUser() {
    this.newUser = new UserInfo();
    this.newUser.firstName = this.firstName?.value;
    this.newUser.lastName = this.lastName?.value;
    this.newUser.pincode = this.pincode?.value;
    this.newUser.dob = this.dob?.value;
    this.newUser.doj = this.doj?.value;

    this.save()

  }

  save() {
    this.userService.createUser(this.newUser)
      .subscribe(response => {
        if (response.message != "Success") {
          alert(response.message)
          return
        } else {
          this.submitted = true;
          this.regID = response.id;
          this.name = response.name;
          this.newUser = new UserInfo();
          console.log(response)
        }
      }, error => console.log(error));

  }
}
