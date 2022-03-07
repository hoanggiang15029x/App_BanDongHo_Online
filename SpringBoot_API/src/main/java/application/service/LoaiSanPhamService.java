package application.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import application.entity.LoaiSanPham;
import application.repository.LoaiSanPhamRepository;

@Service
public class LoaiSanPhamService {
	@Autowired
	private LoaiSanPhamRepository repo;
	
	public List<LoaiSanPham> listAll(){
		return repo.findAll();
	}
	
	public LoaiSanPham get(Integer idLoai) {
		return repo.findOne(idLoai);
	}
	
	public void save(LoaiSanPham loai) {
		repo.save(loai);
	}
	
	public void delete(Integer idLoai) {
		repo.delete(idLoai);
	}
}
