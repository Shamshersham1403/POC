import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { UserInfo } from '../model/user-info';
import { UserServiceService } from '../service/user-service.service';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {
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
  searchForm = new FormGroup({
    search: new FormControl(''),
    searchTxt: new FormControl(''),
    sortKey:new FormControl('')
  })
  get search() { return this.searchForm.get('search'); }
  get searchTxt() { return this.searchForm.get('searchTxt'); }
  get sortKey() { return this.searchForm.get('sortKey'); }

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
  searchResult() {
    var searchKey = this.searchTxt?.value
    if (searchKey == "") {
      // this.getAllActiveUser();
      this.usersList=this.AllActiveUserList

    }
    var cat: string = this.search?.value
    if (cat == '' || cat == null || cat == undefined) {
      alert("Please select the option first")
      this.searchTxt?.setValue('')
      return
    }

    this.usersList = []
    this.AllActiveUserList.filter(item => {
      console.log(searchKey)
      if (cat == 'firstName') {
        return item['firstName']?.includes(searchKey)
      } else if (cat == 'lastName') {
        return item['lastName']?.includes(searchKey)
      } else {
        return item['pincode'] == searchKey;
      }
    }).forEach(item => {
      this.usersList.push(item)
    })

  }

  Sorting() {
    this.userService.sortUserList(this.sortKey?.value).subscribe(response => {
      this.usersList=response
      this.AllActiveUserList=response
      if (this.searchTxt?.value != '')
        this.searchResult()
    })

  }
}
