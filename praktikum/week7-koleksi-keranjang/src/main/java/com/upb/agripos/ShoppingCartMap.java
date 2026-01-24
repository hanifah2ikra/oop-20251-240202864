package com.upb.agripos;

import java.util.HashMap;
import java.util.Map;

public class ShoppingCartMap {

    private Map<String, Product> productMap = new HashMap<>();

    public void tambahProduk(Product product) {
        productMap.put(product.getName(), product);
        System.out.println("Produk ditambahkan (Map): " + product.getName());
    }

    public void hapusProduk(String productName) {
        if (productMap.remove(productName) != null) {
            System.out.println("Produk dihapus (Map): " + productName);
        } else {
            System.out.println("Produk tidak ditemukan (Map): " + productName);
        }
    }

    public double hitungTotal() {
        double total = 0;
        for (Product p : productMap.values()) {
            total += p.getSubtotal();
        }
        return total;
    }
}
