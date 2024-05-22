import { Component, OnInit } from '@angular/core';
import { NgxUiLoaderService } from 'ngx-ui-loader';
import { IProduct } from '../model/product.interface';
import { ProductService } from '../services/product.service';

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.css']
})
export class ProductListComponent implements OnInit {

  productList: IProduct[];

  constructor(private productService: ProductService, private loader: NgxUiLoaderService) {
  }

  ngOnInit(): void {
    this.getProducts();
  }

  getProducts(): void {
    this.loader.start();
    this.productService
        .getProducts()
        .subscribe({
          next: response => {
            this.productList = response;
            this.loader.stop();
          },
          error: error => {
            alert(error.error.message);
          }
        });
  }

}
