package application.api;

import java.text.DecimalFormat;
import java.util.ArrayList;
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

import application.dao.GetCTDonHang;
import application.dao.GetDonHang;
import application.dao.Status;
import application.entity.DonHang;
import application.entity.SanPham;
import application.service.DonHangService;
import application.service.EmailService;
import application.service.KhacHangService;

@CrossOrigin
@RestController
public class DonHangAPI {
	
	@Autowired
	private EmailService emailService;
	@Autowired
	private DonHangService service;
	
	@Autowired
	private KhacHangService khachHangService;
	
	@GetMapping("/donhang")
	public List<GetDonHang> list(){
		List<DonHang> listDonHang = service.listAll();
		List<GetDonHang> getDonHang = new ArrayList<GetDonHang>();
		for(DonHang dh: listDonHang) {
			String diaChi = khachHangService.get(dh.getSdt()).getDiaChi();
			String email = khachHangService.get(dh.getSdt()).getEmail();
			String tenKH = khachHangService.get(dh.getSdt()).getTenKH();
			getDonHang.add(new GetDonHang(dh,diaChi,email,tenKH));
		}
		return getDonHang;
	}
	
	@GetMapping("/donhang/{id}")
	public ResponseEntity<GetDonHang> get(@PathVariable Integer id){
		try {
			DonHang dh = service.get(id);
			String diaChi = khachHangService.get(dh.getSdt()).getDiaChi();
			String email = khachHangService.get(dh.getSdt()).getEmail();
			String tenKH = khachHangService.get(dh.getSdt()).getTenKH();
			// status 200
			return new ResponseEntity<GetDonHang>(new GetDonHang(dh,diaChi,email,tenKH),HttpStatus.OK);
		} catch(NoSuchElementException e) {
			// status 404
			return new ResponseEntity<GetDonHang>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("donhang/sdt/{sdt}")
	public List<GetDonHang> listBySdt(@PathVariable String sdt){
		List<DonHang> listDonHang = service.list(sdt);
		List<GetDonHang> getDonHang = new ArrayList<GetDonHang>();
		for(DonHang dh: listDonHang) {
			String diaChi = khachHangService.get(dh.getSdt()).getDiaChi();
			String email = khachHangService.get(dh.getSdt()).getEmail();
			String tenKH = khachHangService.get(dh.getSdt()).getTenKH();
			getDonHang.add(new GetDonHang(dh,diaChi,email,tenKH));
		}
		return getDonHang;
	}
	
	@PostMapping("/donhang")
	public ResponseEntity<Status> add(@RequestBody DonHang donhang) {
		try {
			donhang.setTrangThai("Chờ duyệt");
			service.save(donhang);
			DonHang check = service.timDH(donhang.getSdt(), donhang.getNgayDatHang());
			return new ResponseEntity<Status>(new Status(check.getIdDH()),HttpStatus.OK);
		} catch(NoSuchElementException e) {
			return new ResponseEntity<Status>(new Status(0),HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@PutMapping("/donhang/{id}")
	public ResponseEntity<?> update(@RequestBody DonHang donhang,@PathVariable Integer id) {
		try {	
			DonHang dh = service.get(id);
			String diaChi = khachHangService.get(dh.getSdt()).getDiaChi();
			String email = khachHangService.get(dh.getSdt()).getEmail();
			String tenKH = khachHangService.get(dh.getSdt()).getTenKH();
			if(!donhang.getTrangThai().equals(dh.getTrangThai())){
				String to = khachHangService.get(donhang.getSdt()).getEmail();
				String subject = "Hello";
				String message = "Hello";  
				DecimalFormat decimalFormat = new DecimalFormat("###,###,###,###");
				String tongtien = decimalFormat.format(Integer.parseInt(dh.getTongTien()));
				if(donhang.getTrangThai().equals("Đang giao")) {
					 subject = "THÔNG BÁO GIAO HÀNG TỪ WATCHSHOP";
					 message = "Đơn hàng của bạn đang được giao trong thời gian sớm nhất.Xin cảm ơn!"
					 		+ "\n Mã đơn hàng: "+ dh.getIdDH()
					 		+ "\n Ngày đặt hàng: "+ dh.getNgayDatHang()
					 		+ "\n Số điện thoại: "+ dh.getSdt()
					 		+ "\n Tổng tiền: " + tongtien + " Đ";
				} else if(donhang.getTrangThai().equals("Đã hủy")) {
					subject = "THÔNG BÁO HỦY ĐƠN HÀNG TỪ WATCHSHOP";
					message = "Đơn hàng của bạn đã bị hủy.Xin lỗi bạn về sự cố này!"
							+ "\n Mã đơn hàng: "+ dh.getIdDH()
					 		+ "\n Ngày đặt hàng: "+ dh.getNgayDatHang()
					 		+ "\n Số điện thoại: "+ dh.getSdt()
					 		+ "\n Tổng tiền: "+ tongtien + " Đ";
				}else if(donhang.getTrangThai().equals("Đã giao")) {
					subject = "THÔNG BÁO GIAO THÀNH CÔNG TỪ WATCHSHOP";
					message = "Đơn hàng của bạn đã được giao thành công.Hãy gửi đánh giá về sản phẩm cho shop nhé!"
							+ "\n Mã đơn hàng: "+ dh.getIdDH()
					 		+ "\n Ngày đặt hàng: "+ dh.getNgayDatHang()
					 		+ "\n Số điện thoại: "+ dh.getSdt()
					 		+ "\n Tổng tiền: "+ tongtien+ " Đ";
				}
				emailService.sendEmail(subject, message, to);
			}
			donhang.setTongTien(dh.getTongTien());
			service.save(donhang);
			return new ResponseEntity<Status>(new Status(1),HttpStatus.OK);
		} catch(NoSuchElementException e) {
			return new ResponseEntity<Status>(new Status(0),HttpStatus.NOT_FOUND);
		}
	}
}
