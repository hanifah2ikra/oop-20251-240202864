# Laporan Praktikum Minggu 6
Topik:  Desain Arsitektur Sistem dengan UML dan Prinsip SOLID

## Identitas
- Nama  : [Hanifah
- NIM   : [240202864
- Kelas : 3IKRA

---

## Tujuan
1. Mahasiswa mampu mengidentifikasi kebutuhan sistem ke dalam diagram UML.
2. Mahasiswa mampu menggambar UML Class Diagram dengan relasi antar class yang tepat.
3. Mahasiswa mampu menjelaskan prinsip desain OOP (SOLID).
4. Mahasiswa mampu menerapkan minimal dua prinsip SOLID dalam kode program.

---

## Dasar Teori

Agri-POS (Agriculture Point of Sale) merupakan sistem informasi penjualan dan pengelolaan hasil pertanian yang dirancang untuk membantu proses bisnis mulai dari pengelolaan produk pertanian, transaksi penjualan, manajemen stok, hingga pembayaran dan pelaporan. Sistem ini melibatkan beberapa aktor seperti Admin, Kasir, Petani, dan Pelanggan, serta dirancang menggunakan pendekatan berorientasi objek dengan menerapkan prinsip SOLID agar mudah dikembangkan dan dipelihara.


---

## Penjelasan Diagram UML

### 1 Use Case Diagram
Use Case Diagram menggambarkan fungsionalitas utama sistem Agri-POS dan interaksi antara aktor dengan sistem.  
Diagram ini berfungsi untuk:
- Mengidentifikasi kebutuhan fungsional (Functional Requirements)
- Menentukan batasan sistem
- Menjadi dasar perancangan diagram lain
Contoh use case utama:
- Kelola Produk
- Proses Transaksi
- Input Hasil Panen
- Lihat Laporan
- Pembayaran

---


### 2 Activity Diagram
Activity Diagram digunakan untuk memodelkan alur proses bisnis dalam sistem, khususnya proses transaksi penjualan.  
Diagram ini menunjukkan:
- Urutan aktivitas dari awal hingga akhir
- Percabangan keputusan (misalnya pengecekan stok)
- Alur normal dan alternatif (stok cukup / tidak cukup)

Activity Diagram membantu memahami logika proses sebelum diimplementasikan ke dalam kode program.

---

### 3 Sequence Diagram
Sequence Diagram menggambarkan interaksi antar objek secara berurutan berdasarkan waktu.  
Pada Agri-POS, Sequence Diagram transaksi menunjukkan:
- Interaksi Kasir dengan antarmuka sistem
- Pemanggilan layanan transaksi
- Pengecekan stok produk
- Proses pembayaran
- Pengembalian hasil transaksi

Diagram ini menjembatani kebutuhan fungsional dengan desain kelas dan metode.

---

### 4 Class Diagram
Class Diagram merepresentasikan struktur statis sistem, meliputi:
- Kelas utama (Product, Transaction, User)
- Service layer (ProductService, TransactionService, PaymentService)
- Interface (PaymentMethod)
- Relasi antar kelas (asosiasi dan pewarisan)

Diagram ini menjadi acuan utama dalam implementasi kode program.

---

### 5 Keterkaitan Antar Diagram
- **Use Case Diagram** → mendefinisikan fungsi sistem
- **Activity Diagram** → menjelaskan alur setiap fungsi
- **Sequence Diagram** → memodelkan interaksi objek saat fungsi dijalankan
- **Class Diagram** → merealisasikan fungsi ke dalam struktur kelas dan interface

Dengan keterkaitan ini, desain sistem menjadi konsisten dari level kebutuhan hingga implementasi.

---


## Penerapan Prinsip SOLID

### 1 Single Responsibility Principle (SRP)
Setiap kelas memiliki satu tanggung jawab utama:
- `ProductService` hanya menangani logika produk
- `PaymentService` hanya menangani pembayaran

### 2 Open/Closed Principle (OCP)
Sistem terbuka untuk pengembangan tetapi tertutup untuk perubahan:
- Penambahan metode pembayaran baru dapat dilakukan dengan membuat kelas baru tanpa mengubah kode yang sudah ada.

### 3 Liskov Substitution Principle (LSP)
Kelas turunan dapat menggantikan kelas induknya:
- `CashPayment` dan `EWalletPayment` dapat digunakan sebagai `PaymentMethod` tanpa mengganggu sistem.

### 4 Interface Segregation Principle (ISP)
Interface dibuat spesifik dan tidak memaksa kelas mengimplementasikan metode yang tidak diperlukan:
- `PaymentMethod` hanya berisi fungsi `processPayment()`.

### 5 Dependency Inversion Principle (DIP)
Modul tingkat tinggi tidak bergantung pada modul tingkat rendah, tetapi pada abstraksi:
- `TransactionService` bergantung pada interface `PaymentMethod`, bukan implementasi konkret.

---

## Traceability Matrix

Tabel berikut menunjukkan keterkaitan antara kebutuhan fungsional, diagram UML, dan realisasi kelas/interface.

| FR | Use Case | Activity / Sequence | Class / Interface |
|----|----------|---------------------|------------------|
| Manajemen Produk | UC-Kelola Produk | Activity Produk | Product, ProductService, ProductRepository |
| Transaksi Penjualan | UC-Proses Transaksi | Activity Transaksi, Seq Transaksi | Transaction, TransactionService |
| Manajemen Stok | UC-Cek Stok | Activity Transaksi | ProductService |
| Pembayaran | UC-Checkout | Seq Pembayaran (Cash/EWallet) | PaymentMethod, CashPayment, EWalletPayment, PaymentService |
| Manajemen Pengguna | UC-Kelola Pengguna | Activity Pengguna | User, UserService |
| Pelaporan | UC-Lihat Laporan | Sequence Laporan | ReportService |

---

## Kode Program

PlantUML (Use Case)
```java

@startuml
left to right direction

actor Admin
actor Kasir
actor Petani
actor Pelanggan

rectangle "Agri-POS System" {
  Admin -- (Kelola Produk)
  Admin -- (Kelola Pengguna)
  Admin -- (Lihat Laporan)

  Kasir -- (Proses Transaksi)
  Kasir -- (Cetak Struk)

  Petani -- (Input Hasil Panen)
  Petani -- (Cek Stok)

  Pelanggan -- (Beli Produk)
  Pelanggan -- (Lihat Riwayat Transaksi)
}
@enduml

```
PlantUML (Class Diagram)
```
@startuml

class Product {
  -id: int
  -name: String
  -price: double
  -stock: int
}

class User {
  -id: int
  -name: String
  -role: String
}

class Transaction {
  -id: int
  -date: Date
  -total: double
}

class Payment {
  +pay(amount: double)
}

interface PaymentMethod {
  +processPayment(amount: double)
}

class CashPayment
class DigitalPayment

PaymentMethod <|-- CashPayment
PaymentMethod <|-- DigitalPayment

Transaction "1" -- "*" Product
Transaction "1" -- "1" User
Transaction --> PaymentMethod

@enduml

```
PlantUML (Sequence Diagram)
```
@startuml
actor Kasir
participant "POS UI" as UI
participant "TransactionService" as TS
participant "ProductService" as PS
participant "PaymentService" as Pay

Kasir -> UI : Input transaksi
UI -> TS : createTransaction()
TS -> PS : checkStock()
PS --> TS : stok tersedia
TS -> Pay : processPayment()
Pay --> TS : pembayaran sukses
TS -> UI : tampilkan struk

@enduml

```
PlantUML (Activity Diagram)
```
@startuml
title Activity Diagram Transaksi Penjualan Agri-POS

start

:Kasir Login;
:Pilih Produk;
:Input Jumlah;

if (Stok tersedia?) then (Ya)
  :Hitung Total Harga;
  :Pilih Metode Pembayaran;
  :Proses Pembayaran;
  :Cetak Struk;
  :Update Stok;
else (Tidak)
  :Tampilkan Pesan\n"Stok Tidak Cukup";
endif

stop
@enduml

```
)
---

## Hasil Eksekusi
(Sertakan screenshot hasil eksekusi program.  
![Screenshot hasil](screenshots/hasil.png)
)
---

## Analisis
(
- Jelaskan bagaimana kode berjalan.  
- Apa perbedaan pendekatan minggu ini dibanding minggu sebelumnya.  
- Kendala yang dihadapi dan cara mengatasinya.  
)
---

## Kesimpulan
Desain arsitektur sistem Agri-POS telah memenuhi kebutuhan fungsional utama dengan menggunakan pendekatan UML dan prinsip SOLID. Struktur sistem menjadi modular, mudah dipahami, dan siap untuk diimplementasikan ke dalam kode program.

### Refleksi dan Potensi Pengembangan
Keunggulan sistem ini adalah:
- Mudah dikembangkan (scalable)
- Kode terstruktur dan maintainable
- Mendukung penambahan fitur baru tanpa perubahan besar

Potensi pengembangan ke depan:
- Integrasi dengan sistem IoT untuk monitoring stok
- Penambahan modul analitik penjualan
- Integrasi payment gateway real-time
- Pengembangan aplikasi mobile untuk petani dan pelanggan

---

## Quiz
1. Jelaskan perbedaan aggregation dan composition serta berikan contoh penerapannya pada desain Anda.
   **Jawaban:** Aggregation adalah hubungan has-a yang lemah.
Objek bagian (part) dapat hidup sendiri meskipun objek induknya dihapus.

Ciri utama:
Siklus hidup objek tidak tergantung pada induknya
Relasi longgar
Biasanya ditandai dengan diamond kosong (◇) pada UML

Contoh pada desain POS:
Kasir — Shift
Toko — Supplier
Toko ◇── Supplier

Penjelasan:
Jika objek Toko dihapus, objek Supplier tetap ada karena supplier bisa melayani toko lain.

Composition adalah hubungan has-a yang kuat.
Objek bagian tidak dapat hidup tanpa objek induknya.

Ciri utama:
Siklus hidup objek bergantung pada induknya
Relasi kuat
Ditandai dengan diamond terisi (◆) pada UML

Contoh pada desain POS:
Transaksi — DetailTransaksi
Order — OrderItem
Transaksi ◆── DetailTransaksi

Penjelasan:
Jika objek Transaksi dihapus, maka semua DetailTransaksi ikut terhapus karena tidak bermakna tanpa transaksi induk.

2. Bagaimana prinsip Open/Closed dapat memastikan sistem mudah dikembangkan?
   **Jawaban:** Kode tidak perlu diubah untuk menambah fitur baru
Sistem diperluas melalui inheritance atau interface
Manfaat OCP
Risiko bug lebih kecil
Mudah dikembangkan
Cocok untuk sistem berskala besar

3. Mengapa Dependency Inversion Principle (DIP) meningkatkan testability? Berikan contoh penerapannya.
   **Jawaban:** Dampak DIP terhadap Testability
Mudah menggunakan mock / stub
Unit test lebih cepat
Tidak bergantung lingkungan eksternal )
