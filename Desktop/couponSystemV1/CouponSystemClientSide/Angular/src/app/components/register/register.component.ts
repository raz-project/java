import { LoginserviceService } from './../../service/loginservice.service';
import { logging } from 'protractor';
import { Customer } from './../../model/Customer';
import { AdminserviceService } from 'src/app/service/adminservice.service';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Company } from 'src/app/model/Company';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  public userName?: string;
  public password?: string;
  public loginType: string;
  public email?:string;
  constructor(private loginService:LoginserviceService,private router:Router) { }

  ngOnInit() {
  }

  public onSubmit(){

  }



}


