package application.entity;

import java.io.Serializable;

public class CTDonHangID implements Serializable {
	private int idDH;
	private int idSP;
	
	
	public CTDonHangID() {
		super();
	}

	public CTDonHangID(int idDH, int idSP) {
		super();
		this.idDH = idDH;
		this.idSP = idSP;
	}
	
}
