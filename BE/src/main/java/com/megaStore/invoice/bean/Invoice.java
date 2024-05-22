package com.megaStore.invoice.bean;

import lombok.Data;

@Data
public class Invoice {

    private int id, customer_id;
    private String status;

}
