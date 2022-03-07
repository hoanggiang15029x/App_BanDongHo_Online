package application.dao;

import application.entity.DonHang;

public class GetDonHang {
	private DonHang donhang;
	private String diacChi;
	private String email;
	private String tenKH;
	public GetDonHang() {
		super();
		// TODO Auto-generated constructor stub
	}
	public GetDonHang(DonHang donhang, String diacChi, String email, String tenKH) {
		super();
		this.donhang = donhang;
		this.diacChi = diacChi;
		this.email = email;
		this.tenKH = tenKH;
	}
	public DonHang getDonhang() {
		return donhang;
	}
	public void setDonhang(DonHang donhang) {
		this.donhang = donhang;
	}
	public String getDiacChi() {
		return diacChi;
	}
	public void setDiacChi(String diacChi) {
		this.diacChi = diacChi;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTenKH() {
		return tenKH;
	}
	public void setTenKH(String tenKH) {
		this.tenKH = tenKH;
	}
}
