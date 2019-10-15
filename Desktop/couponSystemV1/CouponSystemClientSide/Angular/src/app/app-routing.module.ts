import { CustomerCouponsComponent } from './components/customer-coupons/customer-coupons.component';
import { AddCouponComponent } from './components/add-coupon/add-coupon.component';
import { LoginComponent } from './components/login/login.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule, PreloadAllModules } from '@angular/router';
import { ContentComponent } from './components/content/content.component';
import { RegisterComponent } from './components/register/register.component';
import { CompanyComponent } from './components/company/company.component';
import { UpdateCouponComponent } from './components/update-coupon/update-coupon.component';
import { AllCouponsComponent } from './components/all-coupons/all-coupons.component';
import { AdminCompanyDetailComponent } from './components/admin-company-detail/admin-company-detail.component';
import { AdminCustomerDetailComponent } from './components/admin-customer-detail/admin-customer-detail.component';


const routes: Routes = [
    {path: 'content' , component : ContentComponent},
    {path: 'login' , component : LoginComponent},
    {path: 'register' , component : RegisterComponent},
     {path: 'about' , component : ContentComponent},


      // lazy loading
      {path:'admin',loadChildren:'./admin.module#AdminModule'},
      {path: 'company', loadChildren: './company.module#CompanyModule'},
      {path: 'customer', loadChildren: './customer.module#CustomerModule'},
      {path: 'add-coupon' , component : AddCouponComponent},
      {path: 'update-coupon/:id' , component : UpdateCouponComponent},
      {path: 'all-coupons' , component : AllCouponsComponent},
      {path: 'customer-coupons' , component : CustomerCouponsComponent},
      {path: 'admin-company-detail/:id' , component : AdminCompanyDetailComponent},
      {path: 'admin-customer-detail/:id' , component : AdminCustomerDetailComponent},
      {path: '', pathMatch: 'full', redirectTo: '/content'},


];

@NgModule({
  imports: [RouterModule.forRoot(routes, {preloadingStrategy: PreloadAllModules})],
  exports: [RouterModule]
})
export class AppRoutingModule { }
