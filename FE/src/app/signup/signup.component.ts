import { Component } from '@angular/core';
import {CutomerDataService} from "../services/cutomer-data.service";
import {Router} from "@angular/router";
import {ICustomer} from "../model/customer.interface";
import {NgForm} from "@angular/forms";
import {LoginService} from "../services/login.service";

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent {

  firstName: string;
  lastName: string;
  email: string;
  password: string;
  confirmPassword: string;

  constructor(
      private customerDataService: CutomerDataService,
      private loginService: LoginService,
      private router: Router
  ) {}

  signUp(form: NgForm) {
    if (form.invalid) {
      return;
    }

    if (this.password !== this.confirmPassword) {
      alert('Passwords do not match!');
      return;
    }

    // Create a new customer object without the id field
    const newCustomer: Omit<ICustomer, 'id'> = {
      firstName: this.firstName,
      lastName: this.lastName,
      email: this.email,
      password: this.password,
      phoneNumber: '', // Add additional fields as required
      address: '',
      cardNumber: '',
      expiryDate: new Date()
    };

    // Call the login service to create the customer
    this.loginService.createCustomer(newCustomer as ICustomer).subscribe(
        (customer: ICustomer) => {
          // Save the new customer data
          this.customerDataService.setCustomer(customer);

          // Navigate to the products page after signup
          this.router.navigate(['/products']);
        },
        error => {
          alert('Signup failed. Please try again.');
          console.error(error);
        }
    );
  }
}