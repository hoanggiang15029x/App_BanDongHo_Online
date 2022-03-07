package application.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import application.entity.CTDonHang;
import application.entity.CTDonHangID;

public interface CTDonHangRepository extends JpaRepository<CTDonHang, Class<CTDonHangID>> {
	List<CTDonHang> findByIdDH(Integer idDH);
}
