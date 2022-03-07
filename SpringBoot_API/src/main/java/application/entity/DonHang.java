package application.entity;

import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "donhang")
public class DonHang {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_dh")
	private int idDH;
	
	@Column(name = "ngaydathang")
	@JsonFormat(pattern = "dd/MM/yyy HH:mm:ss")
	private Date ngayDatHang;

	@Column(name="sdt")
	private String sdt;
	
	@Column(name = "tongtien")
	private String tongTien;
	
	@Column(name = "trangthai")
	private String trangThai;

	public DonHang() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DonHang(Date ngayDatHang, String sdt, String tongTien, String trangThai) {
		super();
		this.ngayDatHang = ngayDatHang;
		this.sdt = sdt;
		this.tongTien = tongTien;
		this.trangThai = trangThai;
	}

	public Date getNgayDatHang() {
		return ngayDatHang;
	}

	public void setNgayDatHang(Date ngayDatHang) {
		this.ngayDatHang = ngayDatHang;
	}

	public String getSdt() {
		return sdt;
	}

	public void setSdt(String sdt) {
		this.sdt = sdt;
	}
	
	public String getTrangThai() {
		return trangThai;
	}

	public void setTrangThai(String trangThai) {
		this.trangThai = trangThai;
	}

	public int getIdDH() {
		return idDH;
	}
	
	public String getTongTien() {
		return tongTien;
	}

	public void setTongTien(String tongTien) {
		this.tongTien = tongTien;
	}

	@OneToMany(mappedBy = "idDH",cascade = CascadeType.PERSIST)
	private Collection<CTDonHang> ctdonhang;
}

