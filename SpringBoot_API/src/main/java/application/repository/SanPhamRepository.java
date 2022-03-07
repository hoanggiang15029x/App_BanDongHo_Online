package application.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import application.entity.SanPham;

@Repository
public interface SanPhamRepository extends JpaRepository<SanPham, Integer>{
	
//	@Query(value = "SELECT * FROM sanpham sp WHERE sp.trangthai = true",nativeQuery = true)
	@Query(value = "SELECT * FROM sanpham sp WHERE sp.trangthai = 1",nativeQuery = true)
	List<SanPham> getEnable();
	
//	@Query(value = "SELECT * FROM sanpham sp WHERE sp.trangthai = false",nativeQuery = true)
	@Query(value = "SELECT * FROM sanpham sp WHERE sp.trangthai = 0",nativeQuery = true)
	List<SanPham> getDisable();
	
//	@Query(value = "SELECT * FROM sanpham sp WHERE sp.id_loaisp = ?1 and sp.trangthai = true",nativeQuery = true)
	@Query(value = "SELECT * FROM sanpham sp WHERE sp.id_loaisp = ?1 and sp.trangthai = 1",nativeQuery = true)
	List<SanPham> listLoaiSP(Integer idLoai);	
	
//	@Query(value = "SELECT * FROM sanpham sp WHERE sp.id_kieusp = ?1 and sp.trangthai = true",nativeQuery = true)
	@Query(value = "SELECT * FROM sanpham sp WHERE sp.id_kieusp = ?1 and sp.trangthai = 1",nativeQuery = true)
	List<SanPham> listKieuSP(Integer idKieu);
	
//	@Query(value = "SELECT * FROM sanpham sp WHERE sp.id_loaisp = ?1 and sp.id_kieusp = ?2 and sp.trangthai = true",nativeQuery = true)
	@Query(value = "SELECT * FROM sanpham sp WHERE sp.id_loaisp = ?1 and sp.id_kieusp = ?2 and sp.trangthai = 1",nativeQuery = true)
	List<SanPham> timSP(Integer idLoai,Integer idKieu);
}
