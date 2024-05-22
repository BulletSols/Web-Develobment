import { IProduct } from "./product.interface";

export interface ICartItem {
  id: number;
  products: IProduct[];
  quantities: { [key: number]: number };
}