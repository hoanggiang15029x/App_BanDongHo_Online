package application.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import application.entity.CTPhieuNhap;

public interface CTPhieuNhapRepository extends JpaRepository<CTPhieuNhap, Class> {
	List<CTPhieuNhap> findByIdPN(Integer idPN);
}
