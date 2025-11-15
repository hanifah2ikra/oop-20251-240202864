# Laporan Praktikum Minggu 5
Topik: Abstraction (Abstract Class & Interface)

## Identitas
- Nama  : Hanifah
- NIM   : 240202864
- Kelas : 3 IKRA

---

## Tujuan
Mahasiswa mampu menjelaskan perbedaan abstract class dan interface.
Mahasiswa mampu mendesain abstract class dengan method abstrak sesuai kebutuhan kasus.
Mahasiswa mampu membuat interface dan mengimplementasikannya pada class.
Mahasiswa mampu menerapkan multiple inheritance melalui interface pada rancangan kelas.

---

## Dasar Teori
Abstraksi adalah proses menyederhanakan kompleksitas dengan menampilkan elemen penting dan menyembunyikan detail implementasi.
1. Abstract class: tidak dapat diinstansiasi, dapat memiliki method abstrak (tanpa badan) dan non-abstrak. Dapat menyimpan state (field).
2. Interface: kumpulan kontrak (method tanpa implementasi konkret). Sejak Java 8 mendukung default method. Mendukung multiple inheritance (class dapat mengimplementasikan banyak interface).
3. Gunakan abstract class bila ada shared state dan perilaku dasar; gunakan interface untuk mendefinisikan kemampuan/kontrak lintas hierarki.
Dalam konteks Agri-POS, Pembayaran dapat dimodelkan sebagai abstract class dengan method abstrak prosesPembayaran() dan biaya(). Implementasi konkritnya: Cash dan EWallet. Kemudian, interface seperti Validatable (mis. verifikasi OTP) dan Receiptable (mencetak bukti) dapat diimplementasikan oleh jenis pembayaran yang relevan.
---

## Langkah Praktikum


1. Abstract Class – Pembayaran
Buat Pembayaran (abstract) dengan field invoiceNo, total dan method:
   a. double biaya() (abstrak) → biaya tambahan (fee).
   b. boolean prosesPembayaran() (abstrak) → mengembalikan status berhasil/gagal.
   c. double totalBayar() (konkrit) → return total + biaya();.

2. Subclass Konkret
Cash → biaya = 0, proses = selalu berhasil jika tunai >= totalBayar().
EWallet → biaya = 1.5% dari total; proses = membutuhkan validasi.

3. Interface
Validatable → boolean validasi(); (contoh: OTP).
Receiptable → String cetakStruk();

4. Multiple Inheritance via Interface
EWallet mengimplementasikan dua interface: Validatable, Receiptable.
Cash setidaknya mengimplementasikan Receiptable.

5. Main Class
Buat MainAbstraction.java untuk mendemonstrasikan pemakaian Pembayaran (polimorfik).
Tampilkan hasil proses dan struk. Di akhir, panggil CreditBy.print("[NIM]", "[Nama]").


---

## Kode Program
(Tuliskan kode utama yang dibuat, contoh:  

### Pembayaran.java
```java
package com.upb.agripos.model.pembayarann;

public abstract class Pembayaran {
    protected String invoiceNo;
    protected double total;

    public Pembayaran(String invoiceNo, double total) {
        this.invoiceNo = invoiceNo;
        this.total = total;
    }

    // Method abstrak: wajib diimplementasi di subclass
    public abstract double biaya(); 
    public abstract boolean prosesPembayaran(); 

    // Method konkrit: langsung bisa digunakan semua turunan
    public double totalBayar() {
        return total + biaya();
    }

    public String getInvoiceNo() { return invoiceNo; }
    public double getTotal() { return total; }
}

```
### Validatable
```java
package com.upb.agripos.model.kontrak;

public interface Validatable {
    boolean validasi(); // contoh: verifikasi OTP/PIN
}

```
### Receiptable
```java
package com.upb.agripos.model.kontrak;

public interface Receiptable {
    String cetakStruk(); // semua pembayaran bisa mencetak struk
}

```
### Cash.java
```java
package com.upb.agripos.model.pembayarann;

import com.upb.agripos.model.kontrak.Receiptable;

public class Cash extends Pembayaran implements Receiptable {
    private double tunai;

    public Cash(String invoiceNo, double total, double tunai) {
        super(invoiceNo, total);
        this.tunai = tunai;
    }

    @Override
    public double biaya() {
        return 0; // pembayaran tunai tidak ada biaya tambahan
    }

    @Override
    public boolean prosesPembayaran() {
        return tunai >= totalBayar(); // berhasil jika uang cukup
    }

    @Override
    public String cetakStruk() {
        return String.format(
            "INVOICE: %s | TOTAL: %.2f | DIBAYAR: %.2f | KEMBALI: %.2f | STATUS: %s",
            invoiceNo, totalBayar(), tunai, Math.max(0, tunai - totalBayar()),
            prosesPembayaran() ? "BERHASIL" : "GAGAL"
        );
    }
}

```
### EWallet.java 
```java
package com.upb.agripos.model.pembayarann;

import com.upb.agripos.model.kontrak.Validatable;
import com.upb.agripos.model.kontrak.Receiptable;

public class EWallet extends Pembayaran implements Validatable, Receiptable {
    private String akun;
    private String otp;

    public EWallet(String invoiceNo, double total, String akun, String otp) {
        super(invoiceNo, total);
        this.akun = akun;
        this.otp = otp;
    }

    @Override
    public double biaya() {
        return total * 0.015; // 1.5% biaya admin
    }

    @Override
    public boolean validasi() {
        // validasi sederhana: OTP harus 6 digit
        return otp != null && otp.length() == 6;
    }

    @Override
    public boolean prosesPembayaran() {
        return validasi(); // berhasil jika OTP valid
    }

    @Override
    public String cetakStruk() {
        return String.format(
            "INVOICE: %s | TOTAL+FEE: %.2f | E-WALLET: %s | STATUS: %s",
            invoiceNo, totalBayar(), akun,
            prosesPembayaran() ? "BERHASIL" : "GAGAL"
        );
    }
}

```
### MainAbstraction.java
```java
)package com.upb.agripos;

import com.upb.agripos.model.pembayarann.*;
import com.upb.agripos.model.kontrak.*;
import com.upb.agripos.util.CreditBy;

public class MainAbstraction {
    public static void main(String[] args) {
        Pembayaran cash = new Cash("INV-001", 100000, 120000);
        Pembayaran ew = new EWallet("INV-002", 150000, "user@ewallet", "123456");
        Pembayaran tf = new TransferBank("INV-003", 200000, "987654321", "4321");

        System.out.println(((Receiptable) cash).cetakStruk());
        System.out.println(((Receiptable) ew).cetakStruk());
        System.out.println(((Receiptable) tf).cetakStruk());

        CreditBy.print("240202864", "Hanifah");
    }
}
```
### TransferBank.java 
```java
package com.upb.agripos.model.pembayarann;

import com.upb.agripos.model.kontrak.Validatable;
import com.upb.agripos.model.kontrak.Receiptable;

public class TransferBank extends Pembayaran implements Validatable, Receiptable {
    private String norek;
    private String pin;

    public TransferBank(String invoiceNo, double total, String norek, String pin) {
        super(invoiceNo, total);
        this.norek = norek;
        this.pin = pin;
    }

    @Override
    public double biaya() {
        return 3500; // biaya tetap transfer bank
    }

    @Override
    public boolean validasi() {
        // validasi sederhana: pin minimal 4 digit
        return pin != null && pin.length() >= 4;
    }

    @Override
    public boolean prosesPembayaran() {
        return validasi();
    }

    @Override
    public String cetakStruk() {
        return String.format(
            "INVOICE: %s | TOTAL+FEE: %.2f | TRANSFER BANK (%s) | STATUS: %s",
            invoiceNo, totalBayar(), norek,
            prosesPembayaran() ? "BERHASIL" : "GAGAL"
        );
    }
}

```

---

## Hasil Eksekusi
![alt text](<Cuplikan layar 2025-11-10 173746.png>)

---

## Analisis
(
- Jelaskan bagaimana kode berjalan.  
Program ini berhasil menerapkan konsep abstraction, interface, dan polymorphism dalam sistem pembayaran Agri-POS.
Keduanya disimpan sebagai tipe Pembayaran, menunjukkan penggunaan polimorfisme.
Method totalBayar() dari abstract class dijalankan dengan perhitungan berbeda karena method biaya() diimplementasikan secara spesifik pada masing-masing subclass:
Cash → biaya = 0
EWallet → biaya 1.5%
Setelah itu, proses pembayaran dilakukan:
Cash dicek berdasarkan cukup atau tidaknya uang tunai.
EWallet divalidasi menggunakan OTP melalui interface Validatable.
Struk dicetak menggunakan method cetakStruk() melalui interface Receiptable, menghasilkan output berbeda sesuai metode pembayaran.
Pada akhir program, identitas (CreditBy.print) ditampilkan sebagai penutup.
)
---

## Kesimpulan
Pada praktikum ini, konsep abstraction berhasil diterapkan melalui penggunaan abstract class dan interface dalam sistem pembayaran Agri-POS. Abstract class Pembayaran berfungsi sebagai kerangka dasar dengan method abstrak dan konkrit yang kemudian diimplementasikan secara spesifik oleh class turunan seperti Cash dan EWallet. Interface Validatable dan Receiptable digunakan sebagai kontrak perilaku tambahan yang memungkinkan penerapan multiple inheritance secara aman di Java.

---

## Quiz
1. Jelaskan perbedaan konsep dan penggunaan abstract class dan interface. 
   **Jawaban:**  
   a. Abstract class dapat memiliki field, konstruktor, dan method abstrak/non-abstrak.
   b. Interface hanya berisi kontrak perilaku (tanpa state), bisa diimplementasi oleh banyak class (multiple inheritance).

2. Mengapa multiple inheritance lebih aman dilakukan dengan interface pada Java? 
   **Jawaban:** 
    Karena interface tidak membawa state, jadi tidak terjadi konflik pewarisan field atau implementasi (seperti pada multiple inheritance di C++).

3. Pada contoh Agri-POS, bagian mana yang paling tepat menjadi abstract class dan mana yang menjadi interface? Jelaskan alasannya. 
   **Jawaban:** 
   a. Pembayaran → abstract class, karena menyimpan data umum (invoiceNo, total) dan logika dasar (totalBayar()).
   b. Receiptable dan Validatable → interface, karena mendefinisikan perilaku tambahan yang bisa dimiliki berbagai class.
