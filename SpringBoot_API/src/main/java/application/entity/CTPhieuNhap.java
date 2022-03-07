package application.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name = "chitietphieunhap")
@IdClass(CTPhieuNhapID.class)
public class CTPhieuNhap {
	@Id
	@Column(name = "id_pn")
	private int idPN;
	
	@Id
	@Column(name = "id_sp")
	private int idSP;
	
	@Column(name = "soluong")
	private int soLuong;
	
	public CTPhieuNhap() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CTPhieuNhap(int idPN, int idSP, int soLuong) {
		super();
		this.idPN = idPN;
		this.idSP = idSP;
		this.soLuong = soLuong;
	}

	public int getIdPN() {
		return idPN;
	}

	public void setIdPN(int idPN) {
		this.idPN = idPN;
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
