package application.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import application.entity.PhieuNhap;
import application.repository.PhieuNhapRepository;

@Service
public class PhieuNhapService {
	@Autowired
	private PhieuNhapRepository repo;
	
	public List<PhieuNhap> list(){
		return repo.findAll();
	}
	
	public PhieuNhap get(Integer idPN) {
		return repo.findOne(idPN);
	}
	
	public void save(PhieuNhap phieunhap) {
		repo.save(phieunhap);
	}
	
	public PhieuNhap timPN(Date ngayNhap) {
		return repo.timPhieuNhap(ngayNhap);
	}
}
