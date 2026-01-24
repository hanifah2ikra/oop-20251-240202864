# Laporan Praktikum Minggu 9 
Topik:  Exception Handling, Custom Exception, dan Penerapan Design Pattern

## Identitas
- Nama  : Hanifah
- NIM   : 240202864
- Kelas : 3IKRA

---

## Tujuan
1. Menjelaskan perbedaan antara error dan exception.
2. Mengimplementasikan try–catch–finally dengan tepat.
3. Membuat custom exception sesuai kebutuhan program.
4. Mengintegrasikan exception handling ke dalam aplikasi sederhana (kasus keranjang belanja).
---

## Dasar Teori
1. Error vs Exception
   Error adalah kondisi fatal, tidak dapat ditangani (contoh: OutOfMemoryError).
   Exception adalah kondisi tidak normal yang dapat ditangani oleh program.
2. Struktur try–catch–finally
   try adalah  kode yang berpotensi menimbulkan kesalahan
   catch adalah penanganan 
   finally adalah blok yang selalu dijalankan
3. Membuat Custom Exception
   Custom exception adalah exception yang dibuat sendiri oleh programmer untuk menyesuaikan dengan kebutuhan bisnis aplikasi.
---

## Langkah Praktikum
1. Membuat Custom Exception
InvalidQuantityException
ProductNotFoundException
InsufficientStockException
Custom exception digunakan untuk menangani kesalahan spesifik pada proses bisnis keranjang belanja.
2. Membuat Model Product
Class Product digunakan untuk menyimpan informasi produk, meliputi:
Kode produk
Nama produk
Harga
Stok
Class ini berperan sebagai Model dalam konsep MVC.
3. Implementasi ShoppingCart
Class ShoppingCart berfungsi sebagai pengelola data keranjang belanja, dengan fitur:
Menambah produk
Menghapus produk
Melakukan checkout
Pada setiap proses, dilakukan validasi menggunakan custom exception agar kesalahan dapat ditangani dengan tepat.
4. Pengujian Menggunakan Main Program
Class MainExceptionDemo berfungsi sebagai Controller, yang:
Menjalankan alur program
Menangani exception menggunakan try–catch
Menampilkan pesan kesalahan ke terminal
Pengujian dilakukan dengan skenario:
Menambahkan produk dengan jumlah tidak valid
Menghapus produk yang belum ada
Checkout dengan stok tidak mencukupi
5. (Opsional) Penerapan Design Pattern
Singleton digunakan pada ProductService untuk memastikan hanya satu instance layanan yang digunakan.
MVC sederhana diterapkan untuk memisahkan logika bisnis, data, dan tampilan.
---

## Kode Program
1. Membuat Custom Exception
```java
package com.upb.agripos;

public class InvalidQuantityException extends Exception {
    public InvalidQuantityException(String msg) { super(msg); }
}
```

```java
package com.upb.agripos;

public class ProductNotFoundException extends Exception {
    public ProductNotFoundException(String msg) { super(msg); }
}
```

```java
package com.upb.agripos;

public class InsufficientStockException extends Exception {
    public InsufficientStockException(String msg) { super(msg); }
}
```
2. Model Product dengan Stok
```java
package com.upb.agripos;

public class Product {
    private final String code;
    private final String name;
    private final double price;
    private int stock;

    public Product(String code, String name, double price, int stock) {
        this.code = code;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public String getCode() { return code; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getStock() { return stock; }
    public void reduceStock(int qty) { this.stock -= qty; }
}
```
3. Implementasi ShoppingCart dengan Exception Handling
```java
package com.upb.agripos;

import java.util.HashMap;
import java.util.Map;

public class ShoppingCart {
    private final Map<Product, Integer> items = new HashMap<>();

    public void addProduct(Product p, int qty) throws InvalidQuantityException {
        if (qty <= 0) {
            throw new InvalidQuantityException("Quantity harus lebih dari 0.");
        }
        items.put(p, items.getOrDefault(p, 0) + qty);
    }

    public void removeProduct(Product p) throws ProductNotFoundException {
        if (!items.containsKey(p)) {
            throw new ProductNotFoundException("Produk tidak ada dalam keranjang.");
        }
        items.remove(p);
    }

    public void checkout() throws InsufficientStockException {
        for (Map.Entry<Product, Integer> entry : items.entrySet()) {
            Product product = entry.getKey();
            int qty = entry.getValue();
            if (product.getStock() < qty) {
                throw new InsufficientStockException(
                    "Stok tidak cukup untuk: " + product.getName()
                );
            }
        }
        // contoh pengurangan stok bila semua cukup
        for (Map.Entry<Product, Integer> entry : items.entrySet()) {
            entry.getKey().reduceStock(entry.getValue());
        }
    }
}
```
4. Main Program untuk Menguji Exception Handling

```java
package com.upb.agripos;

public class MainExceptionDemo {
    public static void main(String[] args) {
        System.out.println("Hello, I am [HANIFAH]-[240202864] (Week9)");

        ShoppingCart cart = new ShoppingCart();
        Product p1 = new Product("P01", "Pupuk Organik", 25000, 3);

        try {
            cart.addProduct(p1, -1);
        } catch (InvalidQuantityException e) {
            System.out.println("Kesalahan: " + e.getMessage());
        }

        try {
            cart.removeProduct(p1);
        } catch (ProductNotFoundException e) {
            System.out.println("Kesalahan: " + e.getMessage());
        }

        try {
            cart.addProduct(p1, 5);
            cart.checkout();
        } catch (Exception e) {
            System.out.println("Kesalahan: " + e.getMessage());
        }
    }
}
```
---

## Hasil Eksekusi
![alt text](<Cuplikan layar 2026-01-21 001502.png>)
---

## Analisis
Berdasarkan hasil implementasi dan pengujian program, diperoleh beberapa analisis sebagai berikut:
1. Custom exception mempermudah validasi bisnis
Kesalahan seperti jumlah pembelian negatif atau stok tidak cukup dapat ditangani secara spesifik tanpa menghentikan program.
2. Program menjadi lebih robust
Dengan try–catch, program tidak langsung crash ketika terjadi kesalahan input.
3. Pesan error lebih informatif
Pengguna dapat memahami penyebab kesalahan melalui pesan yang jelas, misalnya “Quantity harus lebih dari 0”.
4. Struktur kode lebih rapi dan terorganisir
Pemisahan antara Model, Controller, dan View membuat kode mudah dikembangkan.
5. Exception mendukung proses debugging
Programmer dapat mengetahui letak dan jenis kesalahan dengan lebih cepat.
---

## Kesimpulan
(Berdasarkan praktikum yang telah dilakukan, dapat disimpulkan bahwa:
1. Exception handling sangat penting untuk menjaga stabilitas program Java.
2. Custom exception memungkinkan penerapan aturan bisnis yang lebih jelas dan spesifik.
3. Penerapan try–catch–finally membuat program lebih aman dan profesional.
4. Integrasi exception handling pada sistem keranjang belanja meningkatkan kualitas aplikasi POS.
5. Penerapan design pattern sederhana seperti Singleton dan MVC membantu menciptakan struktur program yang lebih baik.
---

## Quiz
(1. [Jelaskan perbedaan error dan exception.  
   **Jawaban:**
   Error adalah kesalahan serius yang terjadi pada sistem dan tidak dapat ditangani oleh program, biasanya disebabkan oleh keterbatasan sumber daya atau kerusakan sistem, contohnya OutOfMemoryError.

   Exception adalah kesalahan yang terjadi saat program berjalan namun masih dapat ditangani oleh programmer, seperti kesalahan input pengguna atau data yang tidak valid, contohnya InvalidQuantityException.

2. Apa fungsi finally dalam blok try–catch–finally?
   **Jawaban:** Blok finally berfungsi untuk menjalankan kode yang selalu dieksekusi, baik terjadi exception maupun tidak.
   Biasanya digunakan untuk:
   Menutup resource (file, database)
   Membersihkan data sementara
   Menampilkan pesan akhir proses 

3. Mengapa custom exception diperlukan? 
   **Jawaban:** 
   Custom exception diperlukan agar:
   Kesalahan bisnis dapat ditangani secara spesifik
   Pesan error lebih jelas dan informatif
   Kode program lebih terstruktur dan mudah dipelihara
   Proses debugging lebih mudah

4. Berikan contoh kasus bisnis dalam POS yang membutuhkan custom exception
   **Jawaban:**
   Contoh kasus dalam sistem POS:
   Jumlah pembelian ≤ 0 : InvalidQuantityException
   Produk tidak ditemukan di keranjang : ProductNotFoundException
   Stok produk tidak mencukupi saat checkout : InsufficientStockException
   Custom exception membantu mencegah transaksi tidak valid dan menjaga integritas data penjualan. )
