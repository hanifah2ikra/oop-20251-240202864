package com.upb.agripos.view;

import com.upb.agripos.model.Product;

public class ProductConsoleView {

    public void showProduct(Product product) {
        System.out.println("===== PRODUCT INFO =====");
        System.out.println("ID    : " + product.getId());
        System.out.println("Name  : " + product.getName());
        System.out.println("Price : " + product.getPrice());
    }
}
