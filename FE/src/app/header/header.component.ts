import { Component } from '@angular/core';
import {faHome, faShoppingCart, faSignOutAlt} from "@fortawesome/free-solid-svg-icons";
import {Router} from "@angular/router";
import {CutomerDataService} from "../services/cutomer-data.service";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent {

  protected readonly faHome = faHome;
  protected readonly faShoppingCart = faShoppingCart;
  protected readonly faSignOutAlt = faSignOutAlt;
  isLoggedIn: boolean = false;

  constructor(private router: Router, private customerSubService: CutomerDataService) {}

  ngOnInit() {
    this.customerSubService.customer$.subscribe(customer => {
      this.isLoggedIn = !!customer; // If customer is not null, set isLoggedIn to true
    });
  }

  logout() {
    this.customerSubService.clearCustomer(); // Clear customer data to simulate logout
    this.router.navigate(['']);
  }
}
