import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SearchComponent } from './search/search.component';
import { UserInfoComponent } from './user-info/user-info.component';
import { UserListComponent } from './user-list/user-list.component';
  
const routes: Routes = [  
  { path: '', redirectTo: '/', pathMatch: 'full' },  
  { path: 'view-user', component: UserListComponent },  
  { path: 'add-user', component: UserInfoComponent },  
  { path: 'search', component: SearchComponent },  
];  
@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
