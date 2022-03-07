package application.dao;

import javax.persistence.Column;

import application.entity.KhachHang;

public class AddKhachHang {
	private String sdt;
	private String tenKH;
	private String diaChi;
	private String email;
	private String matKhau;
	
	public AddKhachHang(String sdt, String tenKH, String diaChi, String email, String matKhau) {
		super();
		this.sdt = sdt;
		this.tenKH = tenKH;
		this.diaChi = diaChi;
		this.email = email;
		this.matKhau = matKhau;
	}
	
	public AddKhachHang() {
		super();
		// TODO Auto-generated constructor stub
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
