package application.entity;

import java.io.Serializable;

public class DanhGiaID implements Serializable {
	private String sdt;
	
	private String idSP;

	
	public DanhGiaID() {
		super();
		// TODO Auto-generated constructor stub
	}


	public DanhGiaID(String sdt, String idSP) {
		super();
		this.sdt = sdt;
		this.idSP = idSP;
	}	
}
