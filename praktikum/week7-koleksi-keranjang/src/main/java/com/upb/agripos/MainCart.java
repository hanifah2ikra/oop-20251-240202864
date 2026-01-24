package com.upb.agripos;

public class MainCart {
    public static void main(String[] args) {

        ShoppingCart cart = new ShoppingCart();

        Product p1 = new Product("Beras Organik", 15000, 2);
        Product p2 = new Product("Pupuk Cair", 25000, 1);
        Product p3 = new Product("Bibit Cabai", 5000, 5);

        cart.tambahProduk(p1);
        cart.tambahProduk(p2);
        cart.tambahProduk(p3);

        cart.hapusProduk("Pupuk Cair");

        System.out.println("Total Belanja: Rp " + cart.hitungTotal());
    }
}
