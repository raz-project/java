import { Customer } from './../../model/Customer';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AdminserviceService } from 'src/app/service/adminservice.service';

@Component({
  selector: 'app-admin-customer-detail',
  templateUrl: './admin-customer-detail.component.html',
  styleUrls: ['./admin-customer-detail.component.css']
})
export class AdminCustomerDetailComponent implements OnInit {


  public customer : Customer ;

  constructor(private activatedRoute:ActivatedRoute,private adminService:AdminserviceService,private router:Router) { }

  public addClient(){
    this.adminService.updateCustomer(this.customer).subscribe(
      customer=>{
        alert("Customer Succesfully added! the customer id is: " + customer.id);
      },error=>{
        this.router.navigate(["/admin"]).then(()=>{
          window.location.reload();
        });
      });

      alert(`
      Name: ${this.customer.name}
      Password: ${this.customer.password}


      `);


  }


  ngOnInit() {
    this.customer = new Customer();
    const id = +this.activatedRoute.snapshot.params.id;
    this.adminService.getCustomer(id)
    .subscribe(customer => this.customer = customer),
    err=> alert(err.message);
  }


  }



