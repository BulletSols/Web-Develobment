package com.megaStore.product.bean;

import lombok.Data;

@Data
public class Product {

    private int id;
    private String name, description;
    private double price;
    private int quantity;
    private String img_path;

}
