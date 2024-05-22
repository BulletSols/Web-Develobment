import { Component, EventEmitter, Input, Output } from '@angular/core';
import { IProduct } from '../model/product.interface';

@Component({
  selector: 'app-product-item',
  templateUrl: './product-item.component.html',
  styleUrls: ['./product-item.component.css']
})
export class ProductItemComponent {

  @Input() productItem: IProduct;

}
