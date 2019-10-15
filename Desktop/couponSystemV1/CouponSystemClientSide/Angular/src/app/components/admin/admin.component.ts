import { AdminserviceService } from 'src/app/service/adminservice.service';
import { Router } from '@angular/router';
import { Company } from './../../model/Company';
import { Component, OnInit } from '@angular/core';
import { Customer } from 'src/app/model/Customer';
import { get } from 'https';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {

  public company:Company;
  public customer:Customer;
  public type:string;
  public createOn = false;
  public email="";
  public userName="";
  public password="";

  public clients: Company[];






  public updateCalled : boolean = false;

  constructor(private adminService:AdminserviceService , private router:Router){ }

//company funlction


public getCompanies(){

this.adminService.getAllCompany()
.subscribe(clients => this.clients = clients),
  error => alert(error.message);

}

public updateClient(id:number){

  if(this.type==='company'){
    this.router.navigate(['/admin-company-detail/'+id]);
  }
  else if(this.type === 'customer'){
    this.router.navigate(['/admin-customer-detail/'+id]);
  }


 }

 public removeClient(clientId:number){
   this.adminService.removeCompany(clientId)
   .subscribe(text=>alert(text)),
   err =>
   alert(err.message);
   this.router.navigate(["/admin"]).then(()=>{
     window.location.reload();
   });
 }

public getClient(type:string){

  if(type==='company'){
    this.getCompanies();
  }
  else if(type==='customer'){
  this.getCustomers();
  }

}
public getCustomers(){
  this.adminService.getAllCustomers()
  .subscribe(clients=>this.clients = clients),
  err=>alert(err.message);
}

public createClient(){
  this.createOn = false;
  if(this.email){
    this.createCompany();
    this.router.navigate(['/admin']);
  }
  else{
    this.createCustomer();
    this.router.navigate(['/admin']);
  }

}
 private createCompany() {
    this.adminService.addCompany(new Company(0,this.userName,this.password,this.email))
    .subscribe(text => alert(text)),
    err=> alert(err);
  }

  private createCustomer(){
    this.adminService.addCustomer( new Customer(0,this.userName,this.password) )
    .subscribe(text=> alert(text)),
    err=>alert(err.message);
  }

 public changeIndecator(){
   this.createOn = true;
 }

  ngOnInit() {

   this.getCompanies();
   this.type = 'company';


  }



}
