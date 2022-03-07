package application.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="chitietdonhang")
@IdClass(CTDonHangID.class)
public class CTDonHang {
	@Id
	@Column(name = "id_dh")
	private int idDH;
	
	@Id
	@Column(name = "id_sp")
	private int idSP;
	
	@Column(name = "soluong")
	private int soLuong;

	public CTDonHang() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CTDonHang(int idDH, int idSP, int soLuong) {
		super();
		this.idDH = idDH;
		this.idSP = idSP;
		this.soLuong = soLuong;
	}

	public int getIdDH() {
		return idDH;
	}

	public void setIdDH(int idDH) {
		this.idDH = idDH;
	}

	public int getIdSP() {
		return idSP;
	}

	public void setIdSP(int idSP) {
		this.idSP = idSP;
	}

	public int getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}
	
	
}
