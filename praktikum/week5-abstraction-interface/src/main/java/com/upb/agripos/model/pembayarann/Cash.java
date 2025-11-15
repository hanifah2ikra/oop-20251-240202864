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
