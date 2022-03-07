package application.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import application.entity.KhachHang;
import application.repository.KhachHangRepository;

@Service
public class KhacHangService {
	@Autowired
	private KhachHangRepository repo;
	
	public List<KhachHang> listAll(){
		return repo.findAll();
	}
	
	public void save(KhachHang khachhang) {
		repo.save(khachhang);
	}
	
	public KhachHang get(String sdt) {
		return repo.findOne(sdt);
	}
	
	public void delete(String sdt) {
		repo.delete(sdt);
	}
}
