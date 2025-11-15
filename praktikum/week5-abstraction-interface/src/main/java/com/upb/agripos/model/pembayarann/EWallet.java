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
