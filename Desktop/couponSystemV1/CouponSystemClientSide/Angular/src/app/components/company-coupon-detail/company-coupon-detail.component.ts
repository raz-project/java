import { ActivatedRoute } from '@angular/router';
import { Coupon } from './../../model/Coupon';
import { Component, OnInit } from '@angular/core';
import { CompanyserviceService } from 'src/app/service/companyservice.service';

@Component({
  selector: 'app-company-coupon-detail',
  templateUrl: './company-coupon-detail.component.html',
  styleUrls: ['./company-coupon-detail.component.css']
})
export class CompanyCouponDetailComponent implements OnInit {

  public coupon:Coupon;

  constructor(private activatedRoute:ActivatedRoute , private companyService:CompanyserviceService) { }

  ngOnInit() {
    const id:number = +this.activatedRoute.snapshot.params.id;

    this.companyService.getCoupon(id)
    .subscribe(coupon => this.coupon=coupon);
  }

}
