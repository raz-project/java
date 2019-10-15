import { Company } from './../../model/Company';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AdminserviceService } from 'src/app/service/adminservice.service';

@Component({
  selector: 'app-admin-company-detail',
  templateUrl: './admin-company-detail.component.html',
  styleUrls: ['./admin-company-detail.component.css']
})
export class AdminCompanyDetailComponent implements OnInit {

  public company = new Company;




  constructor(private router:Router,private adminService:AdminserviceService, private activatedRoute:ActivatedRoute) { }


  public addClient(){
    this.adminService.updateCompany(this.company).subscribe(
      company=>{
        alert("Company Succesfully added! the company id is: " + company.id);
      },error=>{
        this.router.navigate(["/admin"]).then(()=>{
          window.location.reload();
        });
      });

      alert(`
      Name: ${this.company.name}
      Password: ${this.company.password}
      Email: ${this.company.email}

      `);


  }


  ngOnInit() {
    const id = +this.activatedRoute.snapshot.params.id;
    this.adminService.getCompany(id)
    .subscribe(company => this.company = company),
    err=> alert(err.message);
  }


}
