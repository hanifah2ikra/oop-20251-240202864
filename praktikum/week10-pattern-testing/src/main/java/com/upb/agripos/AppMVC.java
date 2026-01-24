package com.upb.agripos;

import com.upb.agripos.model.Product;
import com.upb.agripos.view.ProductConsoleView;
import com.upb.agripos.controller.ProductController;

public class AppMVC {
    public static void main(String[] args) {

        Product product = new Product(1, "Alat Pertanian", 150000);

        ProductConsoleView view = new ProductConsoleView();

        ProductController controller = new ProductController(product, view);

        controller.showProduct();
    }
}
