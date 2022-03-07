package application.entity;

import java.io.Serializable;

public class CTPhieuNhapID implements Serializable {
	private int idPN;
	private int idSP;
	
	public CTPhieuNhapID() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CTPhieuNhapID(int idPN, int idSP) {
		super();
		this.idPN = idPN;
		this.idSP = idSP;
	}
	
}
