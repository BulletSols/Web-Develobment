import { Component } from '@angular/core';
import {Router} from "@angular/router";
import {LoginService} from "../services/login.service";
import {ICustomer} from "../model/customer.interface";
import {CutomerDataService} from "../services/cutomer-data.service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  constructor(private router: Router, private loginService: LoginService, private customerSubService: CutomerDataService) { }

  username: string;
  password: string;
  customer: ICustomer;

  ngOnInit() {
  }

  login(): void {
    if (this.validate()) {
      this.loginService.getCustomer(this.username, this.password)
          .subscribe({
            next: response => {
              this.customer = response;
              this.customerSubService.setCustomer(this.customer);
              this.router.navigate(["/products"]);
            },
            error: error => {
              alert("Invalid credentials");
            }
          });
    }
  }

  private validate(): boolean{
    return !(this.username == null || this.username === '' || this.password == null || this.password === '');
  }

}