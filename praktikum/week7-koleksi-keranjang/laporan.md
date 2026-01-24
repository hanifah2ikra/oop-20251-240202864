# Laporan Praktikum Minggu 7 
Topik: Collections dan Implementasi Keranjang Belanja

## Identitas
- Nama  : Hanifah
- NIM   : 240202864
- Kelas : 3IKRA

---

## Tujuan
1. Menjelaskan konsep collection dalam Java (List, Map, Set).
2. Menggunakan ArrayList untuk menyimpan dan mengelola objek.
3. Mengimplementasikan Map atau Set sesuai kebutuhan pengelolaan data.
4. Melakukan operasi dasar pada collection: tambah, hapus, dan hitung total.
5. Menganalisis efisiensi penggunaan collection dalam konteks sistem Agri-POS.

---

## Dasar Teori
(1. Java Collections Framework

Java Collections Framework adalah sekumpulan class dan interface yang digunakan untuk menyimpan, mengelola, dan memanipulasi kumpulan objek secara dinamis dan efisien. Framework ini menggantikan penggunaan array statis yang memiliki keterbatasan ukuran dan fleksibilitas.
2. Jenis-Jenis Collection
a. List
List merupakan struktur data yang:
Menyimpan elemen secara terurut
Mengizinkan data duplikat
Menggunakan indeks untuk mengakses elemen
Implementasi yang umum digunakan adalah ArrayList, yang sangat efisien untuk operasi baca dan cocok untuk data berukuran dinamis.
Contoh penggunaan: daftar produk dalam keranjang belanja.
b. Set
Set adalah struktur data yang:
Tidak mengizinkan elemen duplikat
Tidak menjamin urutan penyimpanan
Cocok untuk data yang harus bersifat unik
Implementasi yang sering digunakan adalah HashSet, yang menggunakan mekanisme hashing untuk memastikan tidak ada data ganda.
Contoh penggunaan: daftar kode produk unik atau username.
c. Map
Map menyimpan data dalam bentuk key–value, di mana:
Setiap key harus unik
Value boleh sama
Akses data sangat cepat karena berbasis key
Implementasi yang umum digunakan adalah HashMap.
Contoh penggunaan: data produk dengan kode produk sebagai key dan jumlah (quantity) sebagai value.
3. Collections dalam Sistem POS
Pada sistem Point of Sale (POS) seperti Agri-POS, collections digunakan untuk:
Menyimpan daftar produk yang dibeli
Mengelola jumlah barang
Menghitung total transaksi
Menampilkan data secara terstruktur
Pemilihan jenis collection yang tepat akan berpengaruh langsung terhadap efisiensi dan kemudahan pengelolaan data.)

---

## Langkah Praktikum
(1. Membuat Class Product
Class Product digunakan sebagai representasi objek produk dalam sistem Agri-POS. Class ini menyimpan atribut dasar produk, yaitu kode, nama, dan harga.
Fungsi utama:
Menyimpan data produk
Menyediakan method getter untuk mengakses data
2. Implementasi Keranjang Belanja Menggunakan ArrayList
Class ShoppingCart menggunakan ArrayList untuk menyimpan daftar produk.
Fitur yang diimplementasikan:
addProduct() untuk menambahkan produk ke keranjang
removeProduct() untuk menghapus produk
getTotal() untuk menghitung total harga
printCart() untuk menampilkan isi keranjang
Pendekatan ini cocok untuk keranjang belanja sederhana yang belum memerlukan pengelolaan jumlah barang secara kompleks.
3. Main Program
Class MainCart berfungsi sebagai entry point aplikasi.
Tahapan yang dilakukan:
Menampilkan identitas mahasiswa
Membuat objek produk
Menambahkan produk ke keranjang
Menampilkan isi keranjang
Menghapus produk
Menampilkan isi keranjang setelah perubahan
4. Implementasi Alternatif Menggunakan Map
Sebagai pengembangan, dibuat class ShoppingCartMap yang menggunakan Map<Product, Integer> untuk menyimpan produk dan jumlahnya.
Keunggulan pendekatan ini:
Satu produk tidak disimpan berulang kali
Quantity dikelola secara otomatis
Lebih realistis untuk sistem POS
Fitur yang diimplementasikan:
Penambahan produk beserta quantity
Pengurangan quantity atau penghapusan produk
Perhitungan total harga berbasis quantity)

---

## Kode Program

```java
1. Membuat Class Product
package com.upb.agripos;

public class Product {
    private String name;
    private double price;
    private int quantity;

    public Product(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getSubtotal() {
        return price * quantity;
    }
}

```
```java
2. Implementasi Keranjang dengan ArrayList
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

```
```java
3. Main Program 
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

```
```java
4. Implementasi Alternatif Menggunakan Map (Dengan Quantity)
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

```
)
---

## Hasil Eksekusi
(![alt text](<Cuplikan layar 2026-01-16 210236.png>))
---

## Analisis
(
1. Analisis Penggunaan ArrayList
Penggunaan ArrayList pada keranjang belanja memiliki kelebihan:
Implementasi sederhana
Mudah dipahami oleh pemula
Cocok untuk studi kasus dasar
Namun, ArrayList memiliki keterbatasan:
Tidak dapat mengelola quantity secara langsung
Produk yang sama dapat tersimpan berulang kali
Kurang efisien untuk aplikasi POS berskala besar
2. Analisis Penggunaan Map
Penggunaan Map memberikan solusi atas keterbatasan ArrayList, yaitu:
Produk disimpan sebagai key unik
Quantity disimpan sebagai value
Penghitungan total menjadi lebih akurat
Lebih mendekati sistem POS nyata
Namun, implementasinya lebih kompleks dibandingkan ArrayList, sehingga cocok digunakan setelah memahami konsep dasar collections.
3. Efisiensi dalam Konteks Agri-POS
ArrayList efisien untuk aplikasi sederhana dan pembelajaran awal
Map lebih efisien untuk aplikasi POS yang menangani banyak transaksi dan produk
Pemilihan struktur data harus disesuaikan dengan kebutuhan sistem dan skala aplikasi.  
)
---

## Kesimpulan
(Berdasarkan praktikum yang dilakukan, dapat disimpulkan bahwa:

1. Java Collections Framework menyediakan struktur data yang fleksibel dan efisien.

2. ArrayList cocok digunakan untuk keranjang belanja sederhana karena mudah digunakan dan terurut.

3. Set digunakan untuk mencegah duplikasi data melalui mekanisme hashing.

4. Map sangat efektif untuk pengelolaan data berbasis key–value seperti produk dan quantity.

5. Dalam sistem Agri-POS, penggunaan Map lebih direkomendasikan untuk implementasi lanjutan karena lebih realistis dan efisien.*)

---

## Quiz
(1. Jelaskan perbedaan mendasar antara List, Map, dan Set. 
   **Jawaban:** 
   List : fokus urutan & indeks
   Set : fokus keunikan data
   Map : fokus pencarian data cepat dengan key 

2. Mengapa ArrayList cocok digunakan untuk keranjang belanja sederhana? 
   **Jawaban:** 
   1. Mudah digunakan
      Operasi seperti add(), remove(), dan get() sangat sederhana.
   2. Urutan produk terjaga
      Barang ditampilkan sesuai urutan dimasukkan ke keranjang.
   3. Ukuran dinamis
      Tidak perlu menentukan ukuran awal seperti array.
   4. Akses cepat berdasarkan indeks
      Cocok untuk menampilkan daftar belanja.

3. Bagaimana struktur Set mencegah duplikasi data?
   **Jawaban:** 
   1. Membandingkan nilai hash (hashCode) dan equals()
   2. Jika data dianggap sama → tidak ditambahkan 

4. Kapan sebaiknya menggunakan Map dibandingkan List? Jelaskan dengan contoh.
   **Jawaban:** 
   1. Data memiliki pasangan key–value
   2. Membutuhkan pencarian cepat tanpa looping 
   CONTOH 
   Menggunakan List (tidak efisien)
   List<String> produk = new ArrayList<>();
   produk.add("P001 - Beras");
   Harus looping untuk mencari kode produk.
   Menggunakan Map (lebih tepat)
   Map<String, String> produk = new HashMap<>();
   produk.put("P001", "Beras");
   produk.put("P002", "Gula");
   Ambil data langsung:
   System.out.println(produk.get("P001"));
   Output:
   Beras)



