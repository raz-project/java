

import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { LoginserviceService } from 'src/app/service/loginservice.service';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  public username: string;
   public password: string;
    public loginType: string;

  public constructor(private router: Router, private loginService: LoginserviceService) { }

  public ngOnInit(): void {

  }

  public onSubmit():void{
    this.loginService.login(this.username,this.password,
      this.loginType.toUpperCase())
      .subscribe(()=>{
        this.router.navigate(['/' + this.loginType + '']);
      },
      error => alert("passord or user name invalid"));
  }



}
