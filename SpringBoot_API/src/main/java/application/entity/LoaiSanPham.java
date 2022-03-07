package application.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "loaisanpham")
public class LoaiSanPham {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_loaisp")
	private int idLoaiSP;
	
	@Column(name = "tenloai")
	private String tenLoai;
		
	public LoaiSanPham() {
		super();
	}
	
	public LoaiSanPham(int idLoaiSP) {
		this.idLoaiSP = idLoaiSP;
	}

	public String getTenLoai() {
		return tenLoai;
	}

	public void setTenLoai(String tenLoai) {
		this.tenLoai = tenLoai;
	}

	public int getIdLoaiSP() {
		return idLoaiSP;
	}
		
}
