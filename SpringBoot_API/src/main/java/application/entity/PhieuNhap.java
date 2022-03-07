package application.entity;

import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "phieunhap")
public class PhieuNhap {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_pn")
	private int idPN;
	
	@Column(name = "ngaynhap")
	@JsonFormat(pattern = "dd/MM/yyy HH:mm:ss")
	private Date ngayNhap;
	
	public PhieuNhap(int idPN, Date ngayNhap) {
		super();
		this.idPN = idPN;
		this.ngayNhap = ngayNhap;
	}

	public PhieuNhap() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Date getNgayNhap() {
		return ngayNhap;
	}

	public void setNgayNhap(Date ngayNhap) {
		this.ngayNhap = ngayNhap;
	}

	public int getIdPN() {
		return idPN;
	}
	
		
	@OneToMany(mappedBy = "idPN",cascade = CascadeType.PERSIST)
	private Collection<CTPhieuNhap> ctphieunhap;
}
