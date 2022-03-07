package application.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import application.entity.CTDonHang;
import application.repository.CTDonHangRepository;

@Service
public class CTDonHangService {
	@Autowired
	private CTDonHangRepository repo;
	
	public List<CTDonHang> list(int idDH){
		return repo.findByIdDH(idDH);
	}
	
	public void save(CTDonHang ctdonhang) {
		repo.save(ctdonhang);
	}
}
