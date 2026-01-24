# Laporan Praktikum Minggu 10 
Topik: Design Pattern (Singleton, MVC) dan Unit Testing menggunakan JUnit

## Identitas
- Nama  : Hanifah
- NIM   : 240202864
- Kelas : 3IKRA

---

## Tujuan
1. Menjelaskan konsep dasar design pattern dalam rekayasa perangkat lunak.
2. Mengimplementasikan Singleton Pattern dengan benar.
3. Menjelaskan dan menerapkan Model–View–Controller (MVC) pada aplikasi sederhana.
4. Membuat dan menjalankan unit test menggunakan JUnit.
5. Menganalisis manfaat penerapan design pattern dan unit testing terhadap kualitas perangkat lunak.

---

## Dasar Teori
1. Design Pattern
Design pattern adalah solusi desain yang telah teruji untuk menyelesaikan masalah umum dalam pengembangan perangkat lunak.
2. Singleton Pattern
Tujuan: Menjamin suatu class hanya memiliki satu instance dan menyediakan titik akses global.
3. MVC (Model–View–Controller)
Memisahkan tanggung jawab aplikasi:
Model	Data dan logika bisnis
View	Tampilan/output
Controller	Penghubung Model dan View
---

## Langkah Praktikum
1. Membuat class DatabaseConnection.
2. Menjadikan constructor bersifat private.
3. Menambahkan instance statis.
4. Menyediakan method getInstance().
---

## Kode Program

1. MODEL
```java
/package com.upb.agripos.model;

public class Product {
    private int id;
    private String name;
    private double price;

    public Product(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}

```
2. VIEW
```java
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

```
3. CONTROLLER
```java
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

```
4. MAIN PROGRAM
```java
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

```
---

## Hasil Eksekusi
![alt text](<Cuplikan layar 2026-01-24 220459.png>)
---

## Analisis
1. Manfaat Singleton Pattern
Menghemat sumber daya dengan hanya satu instance
Menghindari konflik akses ke resource global
Memastikan kontrol terpusat terhadap koneksi database
2. Manfaat MVC Pattern
Pemisahan tanggung jawab antara data, tampilan, dan logika
Kode lebih mudah dipelihara dan dikembangkan
Mempermudah debugging dan pengujian
3. Manfaat Unit Testing
Mendeteksi bug lebih awal
Meningkatkan keandalan kode
Memastikan perubahan tidak merusak fungsi lama
4. Risiko Jika Tidak Menggunakan Pattern
Kode sulit dipelihara
Duplikasi instance dan pemborosan resource
Struktur aplikasi tidak terorganisir
---

## Kesimpulan
Penerapan Design Pattern seperti Singleton dan MVC membantu menciptakan struktur kode yang rapi, modular, dan mudah dikembangkan. Unit Testing menggunakan JUnit meningkatkan kualitas perangkat lunak dengan memastikan setiap komponen berjalan sesuai fungsinya. Praktikum ini membuktikan bahwa penggunaan design pattern dan testing sangat penting dalam pengembangan perangkat lunak profesional.

---

## Quiz
(1. Mengapa constructor pada Singleton harus bersifat private?  
   **Jawaban:** Constructor pada Singleton harus private agar tidak ada class lain yang bisa membuat instance baru menggunakan new. Hal ini memastikan bahwa hanya satu instance yang dapat dibuat melalui method getInstance(), sehingga prinsip utama Singleton (single instance) tetap terjaga. 

2. Jelaskan manfaat pemisahan Model, View, dan Controller. 
   **Jawaban:** Pemisahan MVC memberikan beberapa manfaat utama:
Kode lebih terstruktur dan rapi
Mudah dipelihara dan dikembangkan
Memudahkan debugging dan testing
Perubahan pada tampilan tidak memengaruhi logika bisnis
Mendukung kerja tim karena tanggung jawab terpisah
MVC membantu meningkatkan skalabilitas dan keterbacaan kode.

3. Apa peran unit testing dalam menjaga kualitas perangkat lunak?  
   **Jawaban:** Unit testing berperan untuk:
Mendeteksi bug lebih awal
Memastikan fungsi berjalan sesuai harapan
Mencegah error saat ada perubahan kode
Meningkatkan keandalan dan stabilitas sistem
Meningkatkan kepercayaan developer terhadap kualitas kode
Dengan unit testing, kualitas software menjadi lebih terjamin dan konsisten. 
4. Apa risiko jika Singleton tidak diimplementasikan dengan benar?  
   **Jawaban:** Jika Singleton salah diterapkan, dapat menyebabkan:
Terbentuk lebih dari satu instance (melanggar prinsip Singleton)
Pemborosan sumber daya (memory, database connection)
Konflik data atau state antar instance
Bug sulit dilacak karena instance tidak terkontrol
Masalah performa dan stabilitas sistem
Implementasi Singleton yang benar penting untuk menjaga efisiensi dan konsistensi aplikasi. )


