package application.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import application.dao.ThongKe;

@Repository
public interface ThongKeRepository extends JpaRepository<ThongKe, String> {
	@Query(value="EXEC SPThongKe @THANG = ?1,@NAM = ?2",nativeQuery = true)
	List<ThongKe> list(int thang,int nam);
}
