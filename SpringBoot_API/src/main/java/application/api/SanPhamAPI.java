package application.api;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import application.dao.LoaiAndKieu;
import application.dao.Status;
import application.dao.ThongKe;
import application.entity.SanPham;
import application.service.SanPhamService;
import application.service.ThongKeService;

@CrossOrigin
@RestController
public class SanPhamAPI {
	@Autowired
	private SanPhamService service;
	
	@Autowired
	private ThongKeService thongKeService;
	
	@GetMapping(value = {"/","/sanpham"})
	public List<SanPham> listEnable(){
		return service.listEnable();
	}
	
	@GetMapping("/sanphamdaxoa")
	public List<SanPham> listDisable(){
		return service.listDisable();
	}
	
	@GetMapping("/sanpham/{id}")
	public ResponseEntity<SanPham> get(@PathVariable Integer id) {
		try {
			SanPham sanpham = service.get(id);
			return new ResponseEntity<SanPham>(sanpham,HttpStatus.OK);
		} catch(NoSuchElementException e) {
			return new ResponseEntity<SanPham>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/sanpham/kieu-{idKieu}")
	public List<SanPham> listKieu(@PathVariable Integer idKieu){
		return service.listKieu(idKieu);
	}
	
	@GetMapping("/sanpham/loai-{idLoai}")
	public List<SanPham> listLoai(@PathVariable Integer idLoai){
		return service.listLoai(idLoai);
	}
	
	@GetMapping("/sanpham/loai-kieu")
	public List<SanPham> listLoaiandKieu(@RequestBody LoaiAndKieu id){
		return service.listLoaiAndKieu(id.getIdLoai(), id.getIdKieu());
	}
	
	@PostMapping("/sanpham")
	public ResponseEntity<Status> add(@RequestBody SanPham sanpham) {
		try {
			sanpham.setTrangThai(true);
			sanpham.setSoLuongTon(0);
			service.save(sanpham);
			return new ResponseEntity<Status>(new Status(1),HttpStatus.OK);
		}catch(NoSuchElementException e){
			return new ResponseEntity<Status>(new Status(0),HttpStatus.OK);
		}	
	}
	
	@PutMapping("/sanpham/{id}")
	public ResponseEntity<?> update(@RequestBody SanPham sanpham,@PathVariable Integer id) {
		try {
			SanPham exSanPhamEntity = service.get(id);
			if(exSanPhamEntity != null) {
				service.save(sanpham);
				return new ResponseEntity<Status>(new Status(1),HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch(NoSuchElementException e) {
			return new ResponseEntity<Status>(new Status(0),HttpStatus.OK);
		}
	}
	
	@DeleteMapping("/xoasanpham/{id}")
	public ResponseEntity<?> delete(@PathVariable Integer id){
		try {
			SanPham sanPham = service.get(id);
			if(sanPham != null) {
				try{
					service.delete(id);
				}catch(Exception e) {
					sanPham.setTrangThai(false);
					service.save(sanPham);
				}
			}
			return new ResponseEntity<Status>(new Status(1),HttpStatus.OK);
		}catch(NoSuchElementException e) {
			return new ResponseEntity<Status>(new Status(0),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/thongke/{thang}-{nam}")
	public List<ThongKe> listThongKe(@PathVariable(name = "thang") int thang,@PathVariable(name = "nam") int nam){
		return thongKeService.get(thang, nam);
	}
} 