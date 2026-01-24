package com.upb.agripos.controller;

import com.upb.agripos.model.Product;
import com.upb.agripos.view.ProductConsoleView;

public class ProductController {
    private Product product;
    private ProductConsoleView view;

    public ProductController(Product product, ProductConsoleView view) {
        this.product = product;
        this.view = view;
    }

    public void showProduct() {
        view.showProduct(product);
    }
}
