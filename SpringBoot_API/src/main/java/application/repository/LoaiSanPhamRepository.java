package application.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import application.entity.LoaiSanPham;

public interface LoaiSanPhamRepository extends JpaRepository<LoaiSanPham, Integer> {

}
