package application.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import application.entity.CTPhieuNhap;
import application.repository.CTPhieuNhapRepository;

@Service
public class CTPhieuNhapService {
	@Autowired
	private CTPhieuNhapRepository repo;
	
	public List<CTPhieuNhap> list(Integer idPN){
		return repo.findByIdPN(idPN);
	}
	
	public void save(CTPhieuNhap ctphieunhap) {
		repo.save(ctphieunhap);
	}
}
