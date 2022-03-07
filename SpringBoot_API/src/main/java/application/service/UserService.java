package application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import application.entity.CustomUserDetails;
import application.entity.SanPham;
import application.entity.User;
import application.repository.UserRepository;

@Service
public class UserService implements UserDetailsService{
	@Autowired
	private UserRepository userRepository;
	
	@Override
    public UserDetails loadUserByUsername(String username) {
        // Kiểm tra xem user có tồn tại trong database không?
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return new CustomUserDetails(user);
    }

	
	public UserDetails loadUserById(int id) {
		User user = userRepository.findOne(id);
		return new CustomUserDetails(user);
	}
	
	public User findUsername(String tendn) {
		return userRepository.findByUsername(tendn);
	}
	
	public void save(User user) {
		userRepository.save(user);
	}
}
