package application.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import application.dao.ThongKe;
import application.repository.ThongKeRepository;

@Service
public class ThongKeService {	
	@Autowired
	private ThongKeRepository repo;
	
	public List<ThongKe> get(int thang,int nam){
		return repo.list(thang, nam);
	}
}
