package application.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import application.dao.GetCTPhieuNhap;
import application.entity.CTPhieuNhap;
import application.service.CTPhieuNhapService;
import application.service.SanPhamService;

@CrossOrigin
@RestController
public class CTPhieuNhapAPI {
	@Autowired
	private CTPhieuNhapService phieuNhapService;
	
	@Autowired
	private SanPhamService sanPhamService;
	
	@GetMapping("/ctphieunhap/{idPN}")
	private List<GetCTPhieuNhap> list(@PathVariable Integer idPN){
		List<CTPhieuNhap> ctPhieuNhap = phieuNhapService.list(idPN);
		List<GetCTPhieuNhap> getCTPhieuNhap = new ArrayList<GetCTPhieuNhap>();
		for(CTPhieuNhap ctpn: ctPhieuNhap) {
			String tenSP = sanPhamService.get(ctpn.getIdSP()).getTenSP();
			String anhSP = sanPhamService.get(ctpn.getIdSP()).getAnhSP();
			int soLuong = ctpn.getSoLuong();
			int gia = soLuong * sanPhamService.get(ctpn.getIdSP()).getGia();
			
			getCTPhieuNhap.add(new GetCTPhieuNhap(tenSP, anhSP, soLuong, gia));
		}
		return getCTPhieuNhap;
	}
	
	@PostMapping("/ctphieunhap")
	private void add(@RequestBody List<CTPhieuNhap> list) {
		for(CTPhieuNhap item: list) {
			phieuNhapService.save(item);
		}
	}
	
}
