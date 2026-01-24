package com.upb.agripos;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ShoppingCart {

    private List<Product> products = new ArrayList<>();

    // tambahProduk
    public void tambahProduk(Product product) {
        products.add(product);
        System.out.println("Produk ditambahkan: " + product.getName());
    }

    // hapusProduk
    public void hapusProduk(String productName) {
        Iterator<Product> iterator = products.iterator();
        boolean found = false;

        while (iterator.hasNext()) {
            Product p = iterator.next();
            if (p.getName().equalsIgnoreCase(productName)) {
                iterator.remove();
                found = true;
                System.out.println("Produk dihapus: " + productName);
                break;
            }
        }

        if (!found) {
            System.out.println("Produk tidak ditemukan: " + productName);
        }
    }

    // hitungTotal
    public double hitungTotal() {
        double total = 0;
        for (Product p : products) {
            total += p.getSubtotal();
        }
        return total;
    }
}
