import { FormsModule } from '@angular/forms';
import { CompanyComponent } from './components/company/company.component';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes, ActivatedRoute } from '@angular/router';
import { CompanyCouponDetailComponent } from './components/company-coupon-detail/company-coupon-detail.component';
import { AddCouponComponent } from './components/add-coupon/add-coupon.component';
import { UpdateCouponComponent } from './components/update-coupon/update-coupon.component';
import { SharedModeleModule } from './shared-modele.module';

const routs: Routes = [

  {path: '' , component : CompanyComponent},


   {path:'update-coupon',component:UpdateCouponComponent}







];
@NgModule({
  declarations: [CompanyComponent, CompanyCouponDetailComponent, AddCouponComponent, UpdateCouponComponent],
  imports: [
    SharedModeleModule,
    CommonModule,
    RouterModule.forChild(routs),
    FormsModule
  ]
})
export class CompanyModule { }
