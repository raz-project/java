import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AdminComponent } from './components/admin/admin.component';
import { Routes, RouterModule } from '@angular/router';
import { AdminCustomerDetailComponent } from './components/admin-customer-detail/admin-customer-detail.component';
import { AdminCompanyDetailComponent } from './components/admin-company-detail/admin-company-detail.component';
import { SharedModeleModule } from './shared-modele.module';
import { FormsModule } from '@angular/forms';


const routs: Routes = [

  {path: '' , component : AdminComponent},
  {path: 'admin-comany-detail', component: AdminCompanyDetailComponent},
  {path: 'admin-customer-detail', component: AdminCustomerDetailComponent}



];

@NgModule({
  declarations: [AdminComponent, AdminCustomerDetailComponent, AdminCompanyDetailComponent],
  imports: [

    SharedModeleModule,
    CommonModule,
    RouterModule.forChild(routs),
    FormsModule

  ]
})
export class AdminModule { }
