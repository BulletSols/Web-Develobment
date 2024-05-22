import { Injectable } from '@angular/core';
import {BehaviorSubject, Observable} from "rxjs";
import {ICustomer} from "../model/customer.interface";

@Injectable({
  providedIn: 'root'
})
export class CutomerDataService {

  private customerSubject: BehaviorSubject<ICustomer | null>;
  public customer$: Observable<ICustomer | null>;

  constructor() {
    const customerData = localStorage.getItem('customer');
    this.customerSubject = new BehaviorSubject<ICustomer | null>(customerData ? JSON.parse(customerData) : null);
    this.customer$ = this.customerSubject.asObservable();
  }

  setCustomer(customer: ICustomer): void {
    localStorage.setItem('customer', JSON.stringify(customer));
    this.customerSubject.next(customer);
  }

  clearCustomer(): void {
    localStorage.removeItem('customer');
    this.customerSubject.next(null);
  }
}
