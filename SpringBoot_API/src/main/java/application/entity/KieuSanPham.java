package application.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name="kieusanpham")
public class KieuSanPham {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_kieusp")
	private int idKieuSP;
	
	@Column(name = "tenkieu")
	private String tenKieu;
	
	public KieuSanPham() {
		super();
	}

	public KieuSanPham(int idKieuSP) {
		this.idKieuSP = idKieuSP;
	}

	public String getTenKieu() {
		return tenKieu;
	}

	public void setTenKieu(String tenKieu) {
		this.tenKieu = tenKieu;
	}

	public int getIdKieusp() {
		return idKieuSP;
	}
	
}
