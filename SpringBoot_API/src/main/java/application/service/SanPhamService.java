package application.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import application.entity.SanPham;
import application.repository.SanPhamRepository;

@Service
public class SanPhamService {
	
	@Autowired
	private SanPhamRepository repo;
	
	public List<SanPham> listEnable(){
		return repo.getEnable();
	}
	
	public List<SanPham> listDisable(){
		return repo.getDisable();
	}
	
	public List<SanPham> listLoai(Integer idLoai){
		return repo.listLoaiSP(idLoai);
	}
	
	public List<SanPham> listKieu(Integer idKieu){
		return repo.listKieuSP(idKieu);
	}
	
	public List<SanPham> listLoaiAndKieu(Integer idLoai,Integer idKieu){
		return repo.timSP(idLoai, idKieu);
	}
	
	public void save(SanPham sanPham) {
		repo.save(sanPham);
	}
	
	public SanPham get(Integer id) {
		return repo.findOne(id);
	}
	
	public void delete(Integer id) {
		repo.delete(id);
	}
}
