package application.repository;


import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import application.entity.DonHang;

public interface DonHangRepository extends JpaRepository<DonHang, Integer> {
	@Query(value = "SELECT * FROM donhang dh WHERE dh.sdt = ?1",nativeQuery = true)
	List<DonHang> findBySdt(String sdt);
	
	@Query(value = "SELECT * FROM donhang dh WHERE dh.sdt = ?1 and dh.ngaydathang = ?2",nativeQuery = true)
	DonHang timDonHang(String sdt,Date ngayDatHang);
}
