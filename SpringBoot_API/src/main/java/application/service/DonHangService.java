package application.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import application.entity.DonHang;
import application.entity.SanPham;
import application.repository.DonHangRepository;

@Service
public class DonHangService {
	@Autowired
	private DonHangRepository repo;
	
	public List<DonHang> listAll(){
		return repo.findAll();
	}
	
	public void save(DonHang donhang) {
		repo.save(donhang);
	}
	
	public DonHang get(Integer id) {
		return repo.findOne(id);
	}
	
	public List<DonHang> list(String sdt){
		return repo.findBySdt(sdt);
	}
	
	public DonHang timDH(String sdt,Date ngayDatHang) {
		return repo.timDonHang(sdt, ngayDatHang);
	}
}
