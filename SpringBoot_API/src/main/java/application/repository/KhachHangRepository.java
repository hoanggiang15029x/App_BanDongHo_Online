package application.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import application.entity.KhachHang;

public interface KhachHangRepository extends JpaRepository<KhachHang,String> {
	
}
