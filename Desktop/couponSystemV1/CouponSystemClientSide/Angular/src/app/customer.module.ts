import { CustomerComponent } from './components/customer/customer.component';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { CustomerCouponsComponent } from './components/customer-coupons/customer-coupons.component';
import { AllCouponsComponent } from './components/all-coupons/all-coupons.component';
import { SharedModeleModule } from './shared-modele.module';

const routs: Routes = [
  {path: '' , component : CustomerComponent},
 


];
@NgModule({
  declarations: [CustomerComponent, CustomerCouponsComponent, AllCouponsComponent],
  imports: [
    CommonModule,
    SharedModeleModule,
    RouterModule.forChild(routs)
  ]
})
export class CustomerModule { }
