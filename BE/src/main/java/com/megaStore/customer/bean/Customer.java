package com.megaStore.customer.bean;

import lombok.Data;

import java.util.Date;

@Data
public class Customer {

    private int id;
    private Integer cvv;
    private String firstName, lastName, email, password, phoneNumber, address, cardNumber;
    private Date expiryDate;

}
