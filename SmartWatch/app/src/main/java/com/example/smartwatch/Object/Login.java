package com.example.smartwatch.Object;

public class Login {
    public String sdt,hoten,email,diachi,matkhau;

    public Login(String sdt, String hoten, String email, String diachi, String matkhau) {
        this.sdt = sdt;
        this.hoten = hoten;
        this.email = email;
        this.diachi = diachi;
        this.matkhau = matkhau;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public String getMatkhau() {
        return matkhau;
    }

    public void setMatkhau(String matkhau) {
        this.matkhau = matkhau;
    }
}
