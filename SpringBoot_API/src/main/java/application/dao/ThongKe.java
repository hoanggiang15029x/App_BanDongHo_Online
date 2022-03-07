package application.dao;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ThongKe {
	@Id
	private String tensp;
	private int tongsoluong;
		
	public ThongKe() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public ThongKe(String tensp, int tongsoluong) {
		super();
		this.tensp = tensp;
		this.tongsoluong = tongsoluong;
	}

	public String getTensp() {
		return tensp;
	}
	public void setTensp(String tensp) {
		this.tensp = tensp;
	}
	public int getTongsoluong() {
		return tongsoluong;
	}
	public void setTongsoluong(int tongsoluong) {
		this.tongsoluong = tongsoluong;
	}
}
