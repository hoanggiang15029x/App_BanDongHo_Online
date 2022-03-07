package application.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import application.entity.KieuSanPham;
import application.repository.KieuSanPhamRepository;

@Service
public class KieuSanPhamService {
	@Autowired
	private KieuSanPhamRepository repo;
	
	public List<KieuSanPham> listAll(){
		return repo.findAll();
	}
	
	public KieuSanPham get(Integer idKieu) {
		return repo.findOne(idKieu);
	}
	
	public void save(KieuSanPham kieu) {
		repo.save(kieu);
	}
	
	public void delete(Integer idKieu) {
		repo.delete(idKieu);;
	}
}
