package application.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import application.entity.KieuSanPham;

public interface KieuSanPhamRepository extends JpaRepository<KieuSanPham, Integer> {
	
}
