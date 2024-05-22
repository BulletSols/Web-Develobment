import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {IProduct} from "../model/product.interface";
import {ICartItem} from "../model/cart.interface";

@Injectable({
  providedIn: 'root'
})
export class CartService {

  constructor(private http: HttpClient) { }

  getCart(id: number): Observable<ICartItem> {
    return this.http.get<ICartItem>(`http://localhost:8080/cart/${id}`);
  }

  insertUpdateProductToCart(cart: ICartItem): Observable<ICartItem> {
    return this.http.post<ICartItem>(`http://localhost:8080/cart`, cart);
  }

  updateProductToCart(cart: ICartItem): Observable<ICartItem> {
    return this.http.put<ICartItem>(`http://localhost:8080/cart`, cart);
  }

  deleteCart(id: number)  {
    this.http.delete<void>(`http://localhost:8080/cart/${id}`);
  }

}
