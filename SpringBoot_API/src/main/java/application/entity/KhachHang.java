package application.entity;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "khachhang")
public class KhachHang {
	@Id
	private String sdt;
	
	@Column(name = "tenkh")
	private String tenKH;
	
	@Column(name = "diachi")
	private String diaChi;
	
	@Column
	private String email;
	
	public KhachHang() {
		super();
	}

	public KhachHang(String sdt, String tenKH, String diaChi, String email) {
		super();
		this.sdt = sdt;
		this.tenKH = tenKH;
		this.diaChi = diaChi;
		this.email = email;
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
	
	@OneToMany(mappedBy = "sdt",cascade = CascadeType.PERSIST)
	private Collection<DonHang> donhang;
	
	@OneToMany(mappedBy = "sdt",cascade = CascadeType.PERSIST)
	private Collection<DanhGia> danhgia;
}
