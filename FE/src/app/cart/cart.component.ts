import {Component, OnInit} from '@angular/core';
import { ICartItem } from '../model/cart.interface';
import {CartService} from "../services/cart.service";
import {Subscription} from "rxjs";
import {ICustomer} from "../model/customer.interface";
import {CutomerDataService} from "../services/cutomer-data.service";

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit{

  cart: ICartItem;
  emptyCart: boolean = true;
  customerSubscription: Subscription;
  private customer: ICustomer | null = null;


  constructor(private cartService: CartService, private customerSubService: CutomerDataService) {
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
        this.cart = response;
        this.emptyCart = !(this.cart != null && this.cart.quantities);
      },
      error: error => {
        alert(error.error.message);
      }
    });
  }

  validateQuantity(selectedQuantity: number, maxQuantity: number, index: number): boolean {
    let helpline = (document.getElementsByClassName('validate').item(index)) as HTMLElement;
    let buttons = document.getElementsByTagName('button');
    if (helpline)
      if (selectedQuantity > maxQuantity){
        helpline.style.display = 'block';
        for (let i = 0; i < buttons.length; i++) {
          let button = (buttons.item(i) as HTMLButtonElement);
          button.disabled = true;
          button.style.backgroundColor = 'gray';
        }
        return false;
      }
      else{
        helpline.style.display = 'none';
        for (let i = 0; i < buttons.length; i++) {
          let button = (buttons.item(i) as HTMLButtonElement);
          button.disabled = false;
          button.style.backgroundColor = '#3b88f4a7';
        }
        return true;
      }
    return false;
  }

  updateCart(){
    let quantities = document.getElementsByTagName('input');
    for (let i = 1; i <= quantities.length; i++) {
      this.cart.quantities[i] = parseInt((document.getElementsByTagName('input').item(i - 1) as HTMLInputElement).value);
    }

    this.cartService.updateProductToCart(this.cart).subscribe({
      next: response => {
      },
      error: error => {
        alert(error.error.message);
      }
    });

  }


  public totalPrice(id: number): number {
    if (!this.emptyCart)
    { // @ts-ignore
      return this.cart.products.find(p => p.id == id)?.price * this.cart.quantities[id];
    }
    else
      return 0;
  }

  ngOnDestroy() {
    // Unsubscribe to avoid memory leaks
    if (this.customerSubscription) {
      this.customerSubscription.unsubscribe();
    }
  }


}
