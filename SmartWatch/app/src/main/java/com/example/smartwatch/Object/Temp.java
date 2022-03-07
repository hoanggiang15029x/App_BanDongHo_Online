package com.example.smartwatch.Object;

public class Temp {
    public String sdt,tenKH,diaChi,email,matKhau;

    public Temp(String sdt, String tenKH, String diaChi, String email, String matKhau) {
        this.sdt = sdt;
        this.tenKH = tenKH;
        this.diaChi = diaChi;
        this.email = email;
        this.matKhau = matKhau;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getTenKH() {
        return tenKH;
    }

    public void setTenKH(String tenKH) {
        this.tenKH = tenKH;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }
}
