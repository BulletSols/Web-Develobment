import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { NgxUiLoaderService } from 'ngx-ui-loader';
import { IProduct } from '../model/product.interface';
import { ProductService } from '../services/product.service';
import {faCartPlus} from "@fortawesome/free-solid-svg-icons";
import {ConfirmDialogueComponent} from "../confirm-dialogue/confirm-dialogue.component";
import {MatDialog} from "@angular/material/dialog";
import {CartService} from "../services/cart.service";
import {ICartItem} from "../model/cart.interface";
import {Subscription} from "rxjs";
import {CutomerDataService} from "../services/cutomer-data.service";
import {ICustomer} from "../model/customer.interface";

@Component({
  selector: 'app-product-details',
  templateUrl: './product-details.component.html',
  styleUrls: ['./product-details.component.css']
})
export class ProductDetailsComponent implements OnInit {

  product: IProduct;
  productList: IProduct[];
  selectedQuantity: number = 1; // Default selected value//
  customerSubscription: Subscription;
  private customer: ICustomer | null = null;
  protected readonly faCartPlus = faCartPlus;


    constructor(private route: ActivatedRoute,
                private productService: ProductService,
                private ngxLoader: NgxUiLoaderService,
                public dialog: MatDialog,
                private cartService: CartService,
                private customerSubService: CutomerDataService) {
    }

  ngOnInit(): void {
    this.ngxLoader.start();
    const id = Number(this.route.snapshot.paramMap.get('id'));

    this.productService
        .getProduct(id)
        .subscribe({
          next: response => {
            this.product = response;
          },
          error: err => {
            alert(err.messgae);
          }
        })
        .add(() => this.ngxLoader.stop())

      this.productService
          .getProducts()
          .subscribe({
              next: response => {
                  this.productList = response;
              },
              error: error => {
                  alert(error.error.message);
              }
          });
  }

  addToCart() {
        if (!this.validateQuantity())
            alert("The quantity chosen must be lower than the quantity available");
      else {
          this.openDialog();
        }
    
  }

    validateQuantity(): boolean {
        let helpline = document.getElementById('validate');
        if (helpline)
        if (this.selectedQuantity > this.product.quantity){
            helpline.style.display = 'block';
            return false;
        }
        else{
            helpline.style.display = 'none';
            return true;
        }
        return false;
    }

    openDialog(): void {
        const dialogRef = this.dialog.open(ConfirmDialogueComponent);

        dialogRef.afterClosed().subscribe(result => {
            if (result) {
                this.customerSubscription = this.customerSubService.customer$.subscribe( customer => this.customer = customer);
                let cartItem: ICartItem = {
                    id: this.customer?.id!,
                    products: [],
                    quantities: { [this.product.id]: this.selectedQuantity },
                }
                console.log(cartItem);
                this.cartService.insertUpdateProductToCart(cartItem).subscribe(x=> console.log(x));
                this.product.quantity -= this.selectedQuantity;
            }
        });
    }

    ngOnDestroy() {
        // Unsubscribe to avoid memory leaks
        if (this.customerSubscription) {
            this.customerSubscription.unsubscribe();
        }
    }
}
