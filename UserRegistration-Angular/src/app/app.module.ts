import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';  

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { UserInfoComponent } from './user-info/user-info.component';
import { UserListComponent } from './user-list/user-list.component';
import { BsDatepickerModule } from 'ngx-bootstrap/datepicker';
import { SearchComponent } from './search/search.component';  


@NgModule({
  declarations: [
    AppComponent,
    UserInfoComponent,
    UserListComponent,
    SearchComponent
  ],
  imports: [
    BsDatepickerModule.forRoot(),
    BrowserModule,  
    AppRoutingModule,  
    FormsModule,  
    ReactiveFormsModule,  
    HttpClientModule,  
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
