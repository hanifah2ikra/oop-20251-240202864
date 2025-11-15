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
