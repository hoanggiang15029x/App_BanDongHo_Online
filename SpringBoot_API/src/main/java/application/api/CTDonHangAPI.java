package application.api;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import application.dao.GetCTDonHang;
import application.entity.CTDonHang;
import application.service.CTDonHangService;
import application.service.SanPhamService;

@CrossOrigin
@RestController
public class CTDonHangAPI {
	@Autowired
	private CTDonHangService service;
	
	@Autowired
	private SanPhamService sanPhamService;
	
	@GetMapping("ctdonhang/{idDH}")
	public List<GetCTDonHang> list(@PathVariable Integer idDH){
		List<CTDonHang> listCTDH = service.list(idDH);
		List<GetCTDonHang> getCTDonHang = new ArrayList<GetCTDonHang>();
		for(CTDonHang ctdh: listCTDH) {
			String tenSP = sanPhamService.get(ctdh.getIdSP()).getTenSP();
			String anhSP = sanPhamService.get(ctdh.getIdSP()).getAnhSP();
			int soLuong = ctdh.getSoLuong();
			int gia = ctdh.getSoLuong() * sanPhamService.get(ctdh.getIdSP()).getGia();
			getCTDonHang.add(new GetCTDonHang(tenSP, anhSP, soLuong, gia));
		}
		return getCTDonHang;
	}
	
	@PostMapping("ctdonhang")
	public ResponseEntity<?> add(@RequestBody List<CTDonHang> list) {
		try{
			for(CTDonHang item:list) {
				service.save(item);
			}
			return ResponseEntity.ok(1);
		}catch(NoSuchElementException e) {
			return ResponseEntity.ok(0);
		}
	}
}
