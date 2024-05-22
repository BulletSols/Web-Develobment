package com.megaStore.cart;

import com.megaStore.product.bean.Product;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class Cart {
    private int id;
    private List<Product> products;
    private Map<Integer, Integer> quantities; //products with selected quantity of each product

}
