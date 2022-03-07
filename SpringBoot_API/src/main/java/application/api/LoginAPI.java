package application.api;

import java.util.NoSuchElementException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import application.dao.ChangePassword;
import application.dao.LoginRequest;
import application.dao.LoginResponse;
import application.entity.CustomUserDetails;
import application.entity.SanPham;
import application.entity.User;
import application.jwt.JwtTokenProvider;
import application.service.UserService;

@CrossOrigin
@RestController
public class LoginAPI {
	@Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider tokenProvider;
    
    @Autowired
    private UserService service;
    @PostMapping("/login")
    public LoginResponse authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        // Xác thực từ username và password.
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getTendn(),
                        loginRequest.getMatkhau()
                )
        );
        // Nếu không xảy ra exception tức là thông tin hợp lệ
        // Set thông tin authentication vào Security Context       
        SecurityContextHolder.getContext().setAuthentication(authentication);
        // Trả về jwt cho người dùng.
        CustomUserDetails userDetail = (CustomUserDetails) authentication.getPrincipal();
        String jwt = tokenProvider.generateToken(userDetail);
        return new LoginResponse(jwt,userDetail.getUser().getRole());
    }
    
    @PutMapping("/doimatkhau")
    private int change(@RequestBody ChangePassword change) { 	  	
    	 Authentication authentication = authenticationManager.authenticate(
                 new UsernamePasswordAuthenticationToken(
                         change.getTendn(),
                         change.getMatkhau()
                 )
         );   
         SecurityContextHolder.getContext().setAuthentication(authentication);
         String jwt = tokenProvider.generateToken((CustomUserDetails) authentication.getPrincipal());
         
         if(jwt != null) {
        	 try {
     			User exUser = service.findUsername(change.getTendn());
     			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
     			if(exUser != null) {
     				User newPass = new User(exUser.getId(),exUser.getUsername(),encoder.encode(change.getMatKhauMoi()),exUser.getRole());
     				System.out.println(newPass.toString());
     				service.save(newPass);
     				return 1;
     			}
         		
     		} catch(NoSuchElementException e) {
     			 new ResponseEntity<>(HttpStatus.NOT_FOUND);
     		}
         }
         return 0;
    }
    
    

}
