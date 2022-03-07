package application.dao;

public class GetCTDonHang {
	private String tenSP;
	private String anhSP;
	private int soLuong;
	private int gia;
	
	public GetCTDonHang(String tenSP, String anhSP, int soLuong,int gia) {
		super();
		this.tenSP = tenSP;
		this.anhSP = anhSP;
		this.soLuong = soLuong;
		this.gia = gia;
	}
	
	public String getTenSP() {
		return tenSP;
	}
	public void setTenSP(String tenSP) {
		this.tenSP = tenSP;
	}
	public String getAnhSP() {
		return anhSP;
	}
	public void setAnhSP(String anhSP) {
		this.anhSP = anhSP;
	}
	public int getSoLuong() {
		return soLuong;
	}
	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}

	public int getGia() {
		return gia;
	}

	public void setGia(int gia) {
		this.gia = gia;
	}
	
}
