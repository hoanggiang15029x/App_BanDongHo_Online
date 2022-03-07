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

import application.entity.KieuSanPham;
import application.entity.LoaiSanPham;
import application.service.KieuSanPhamService;
import application.service.LoaiSanPhamService;

@CrossOrigin
@RestController
public class PLSanPhamAPI {
	@Autowired
	private KieuSanPhamService kieuService;
	
	@Autowired
	private LoaiSanPhamService loaiService;
	
	@GetMapping("/kieu")
	public List<KieuSanPham> listKieu(){
		return kieuService.listAll();
	}
	
	@GetMapping("/loai")
	public List<LoaiSanPham> listLoai(){
		return loaiService.listAll();
	}
	
	@GetMapping("/kieu/{idKieu}")
	public ResponseEntity<KieuSanPham> getKieu(@PathVariable(name = "idKieu") Integer id) {
		try {
			KieuSanPham kieusanpham = kieuService.get(id);
			return new ResponseEntity<KieuSanPham>(kieusanpham,HttpStatus.OK);
		} catch(NoSuchElementException e) {
			return new ResponseEntity<KieuSanPham>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/loai/{idLoai}")
	public ResponseEntity<LoaiSanPham> getLoai(@PathVariable(name = "idLoai") Integer id) {
		try {
			LoaiSanPham loaisanpham = loaiService.get(id);
			return new ResponseEntity<LoaiSanPham>(loaisanpham,HttpStatus.OK);
		} catch(NoSuchElementException e) {
			return new ResponseEntity<LoaiSanPham>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("/kieu")
	public void add(@RequestBody KieuSanPham kieusanpham) {
		kieuService.save(kieusanpham);
	}
	
	@PostMapping("/loai")
	public void add(@RequestBody LoaiSanPham loaisanpham) {
		loaiService.save(loaisanpham);
	}
	
	@PutMapping("/kieu/{id}")
	public ResponseEntity<?> updateKieu(@RequestBody KieuSanPham kieusanpham,@PathVariable Integer id) {
		try {
			KieuSanPham exKieu = kieuService.get(id);
			kieuService.save(kieusanpham);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch(NoSuchElementException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping("/loai/{id}")
	public ResponseEntity<?> updateLoai(@RequestBody LoaiSanPham loaisanpham,@PathVariable Integer id) {
		try {
			LoaiSanPham exLoai = loaiService.get(id);
			loaiService.save(loaisanpham);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch(NoSuchElementException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/kieu/{id}")
	public ResponseEntity<?> deleteKieu(@PathVariable Integer id){
		try {
			KieuSanPham exKieu = kieuService.get(id);
			kieuService.delete(id);
			return new ResponseEntity<>(HttpStatus.OK);
		}catch(NoSuchElementException e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/loai/{id}")
	public ResponseEntity<?> deleteLoai(@PathVariable Integer id){
		try {
			LoaiSanPham exLoai = loaiService.get(id);
			loaiService.delete(id);
			return new ResponseEntity<>(HttpStatus.OK);
		}catch(NoSuchElementException e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
}
