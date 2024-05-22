import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {ICustomer} from "../model/customer.interface";

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(private http: HttpClient) { }

  getCustomer(email: string , password:string): Observable<ICustomer> {
    let credinials = {
      email: email,
      password: password
    }
    return this.http.post<ICustomer>('http://localhost:8080/customer/login', credinials);
  }

  createCustomer(customer: ICustomer): Observable<ICustomer>{
    return this.http.post<ICustomer>('http://localhost:8080/customer/signup', customer);
  }
}
