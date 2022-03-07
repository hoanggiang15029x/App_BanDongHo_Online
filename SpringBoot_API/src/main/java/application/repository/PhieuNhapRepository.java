package application.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import application.entity.PhieuNhap;

public interface PhieuNhapRepository extends JpaRepository<PhieuNhap, Integer> {
	@Query(value = "SELECT * FROM phieunhap ph WHERE ph.ngaynhap = ?1",nativeQuery = true)
	PhieuNhap timPhieuNhap(Date ngayNhap);
}
