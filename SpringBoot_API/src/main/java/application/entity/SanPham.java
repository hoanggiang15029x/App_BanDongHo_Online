package application.entity;

import java.util.Collection;

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

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Entity
@Table(name = "sanpham")
public class SanPham {
	
	@Id
	@Column(name = "id_sp")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idSP;

	@Column(name = "tensp")
	private String tenSP;

	@Column(name = "gia")
	private int gia;

	@Column(name = "anhsp")
	private String anhSP;

	@Column(name = "mota")
	private String moTa;

	@ManyToOne
	@JoinColumn(name = "id_loaisp")
	private LoaiSanPham loaiSP;

	@ManyToOne
	@JoinColumn(name = "id_kieusp")
	private KieuSanPham kieuSP;
	
	
	@Column(name = "soluongton")
	private int soLuongTon;
	
	@Column(name = "trangthai")
	private boolean trangThai;
	
	public SanPham() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SanPham(int idSP) {
		super();
		this.idSP = idSP;
	}

	public String getTenSP() {
		return tenSP;
	}

	public void setTenSP(String tenSP) {
		this.tenSP = tenSP;
	}

	public int getGia() {
		return gia;
	}

	public void setGia(int gia) {
		this.gia = gia;
	}

	public String getAnhSP() {
		return ServletUriComponentsBuilder.fromCurrentContextPath().path("/image/").path(anhSP).toUriString();
	} 

	public void setAnhSP(String anhSP) {
		this.anhSP = anhSP;
	}

	public String getMoTa() {
		return moTa;
	}

	public void setMoTa(String moTa) {
		this.moTa = moTa;
	}

	public LoaiSanPham getLoaiSP() {
		return loaiSP;
	}

	public void setLoaiSP(LoaiSanPham loaiSP) {
		this.loaiSP = loaiSP;
	}

	public KieuSanPham getKieuSP() {
		return kieuSP;
	}

	public void setKieuSP(KieuSanPham kieuSP) {
		this.kieuSP = kieuSP;
	}
	
	public int getSoLuongTon() {
		return soLuongTon;
	}

	public void setSoLuongTon(int soLuongTon) {
		this.soLuongTon = soLuongTon;
	}

	public int getIdSP() {
		return idSP;
	}
	
	public boolean isTrangThai() {
		return trangThai;
	}

	public void setTrangThai(boolean trangThai) {
		this.trangThai = trangThai;
	}

	@OneToMany(mappedBy = "idSP",cascade = CascadeType.ALL)
	private Collection<CTPhieuNhap> ctphieunhap;
	
	@OneToMany(mappedBy = "idSP",cascade = CascadeType.PERSIST)
	private Collection<CTDonHang> ctdonhang;

	@OneToMany(mappedBy = "idSP",cascade = CascadeType.ALL)
	private Collection<DanhGia> danhgia;
}
