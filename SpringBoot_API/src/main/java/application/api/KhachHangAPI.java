package application.api;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import application.dao.AddKhachHang;
import application.dao.Status;
import application.entity.KhachHang;
import application.entity.SanPham;
import application.entity.User;
import application.service.KhacHangService;
import application.service.UserService;

@CrossOrigin
@RestController
public class KhachHangAPI {
	@Autowired
	private KhacHangService service;
	
	@Autowired
	private UserService userservice;
	
	@GetMapping("/khachhang")
	private List<KhachHang> list(){
		return service.listAll();
	}
	
	@GetMapping("/khachhang/{sdt}")
	private ResponseEntity<KhachHang> get(@PathVariable String sdt){
		try {
			KhachHang khachhang= service.get(sdt);
			return new ResponseEntity<KhachHang>(khachhang,HttpStatus.OK);
		}catch(NoSuchElementException e) {
			return new ResponseEntity<KhachHang>(HttpStatus.NOT_FOUND);
		}		
	}
	
	@GetMapping("/check/{sdt}")
	private ResponseEntity<Status> getCheck(@PathVariable String sdt){
		try {
			KhachHang khachhang= service.get(sdt);
			if(khachhang == null) {
				return new ResponseEntity<Status>(new Status(1),HttpStatus.OK);
			}
			return new ResponseEntity<Status>(new Status(2),HttpStatus.OK);
		}catch(NoSuchElementException e) {
			return new ResponseEntity<Status>(new Status(0),HttpStatus.OK);
		}		
	}
	
	@PostMapping("/khachhang")
	private ResponseEntity<Status> add(@RequestBody AddKhachHang addKhachHang) {
		try {
			KhachHang exKhachHang = service.get(addKhachHang.getSdt());
			if(exKhachHang == null) {
				service.save(new KhachHang(addKhachHang.getSdt(),addKhachHang.getTenKH(),addKhachHang.getDiaChi(),addKhachHang.getEmail()));
				BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
				User user = new User(addKhachHang.getSdt(),encoder.encode(addKhachHang.getMatKhau()),"USER");
				userservice.save(user);
				return new ResponseEntity<Status>(new Status(1),HttpStatus.OK);
			}
			return new ResponseEntity<Status>(new Status(2),HttpStatus.OK);
		}catch(NoSuchElementException e) {
			return new ResponseEntity<Status>(new Status(0),HttpStatus.OK);
		}
	}
	
	
	@PutMapping("/khachhang/{sdt}")
	private ResponseEntity<?> update(@RequestBody KhachHang khachhang,@PathVariable String sdt){
		try {
			KhachHang exKhachHang = service.get(sdt);
			if(exKhachHang != null) {
				service.save(khachhang);
				return new ResponseEntity<Status>(new Status(1),HttpStatus.OK);
			}
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}catch(NoSuchElementException e) {
			return new ResponseEntity<Status>(new Status(0),HttpStatus.OK);
		}
	}
	
	@DeleteMapping("/khachhang/{sdt}")
	private ResponseEntity<?> delete(@PathVariable String sdt){
		try {
			KhachHang exKhachHang = service.get(sdt);
			service.delete(sdt);
			return new ResponseEntity<>(HttpStatus.OK);
		}catch(NoSuchElementException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
