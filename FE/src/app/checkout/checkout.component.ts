import {Component, OnInit} from '@angular/core';
import {ICartItem} from "../model/cart.interface";
import {Subscription} from "rxjs";
import {ICustomer} from "../model/customer.interface";
import {CartService} from "../services/cart.service";
import {CutomerDataService} from "../services/cutomer-data.service";

@Component({
  selector: 'app-checkout',
  templateUrl: './checkout.component.html',
  styleUrls: ['./checkout.component.css']
})
export class CheckoutComponent implements OnInit {

  cartItems: ICartItem;
  shippingCost: number = 10;  // Example fixed shipping cost
  selectedPaymentMethod: string = 'creditCard';
  quantities: { [key: number]: number } = {};

  customerSubscription: Subscription;
  private customer: ICustomer | null = null;


  constructor(private cartService: CartService, private customerSubService: CutomerDataService) {
  }

  get subtotal(): number {
    return this.cartItems.products.reduce((acc, product) =>
        acc + (product.price * this.quantities[product.id]), 0);
  }

  get total(): number {
    console.log(this.subtotal);
    return this.subtotal + this.shippingCost;
  }

  ngOnInit(): void {
    this.customerSubscription = this.customerSubService.customer$.subscribe(customer => {
      this.customer = customer;
      if (this.customer && this.customer.id) {
        this.loadCart(this.customer.id);
      }
    });
  }

  private loadCart(customerId: number): void {
    this.cartService.getCart(customerId).subscribe({
      next: response => {
        this.cartItems = response;
        this.quantities = this.cartItems.quantities;
      },
      error: error => {
        alert(error.error.message);
      }
    });
  }

  checkout(): void {
    // Implement checkout logic here
    alert(`Checking out with payment method: ${this.selectedPaymentMethod}`);
  }
}
