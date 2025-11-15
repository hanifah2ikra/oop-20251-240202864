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
