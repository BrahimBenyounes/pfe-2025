package com.bys.login.service;



import com.bys.login.entity.Users;
import com.bys.login.repository.UsersRepo;
import com.bys.login.requests.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class UserService {
	
	@Autowired
	UsersRepo usersRepo;
	
	public Users addUser(Users user) {
		
		return usersRepo.save(user);
		
	}
	
	public Boolean loginUser(LoginRequest loginRequest) {
		
		Optional<Users> user = usersRepo.findById(loginRequest.getUserId());
		Users user1 = user.get();
		
		if(user1 == null) {
			return false;
		}
		
		
		
		if(!user1.getPassword().equals(loginRequest.getPassword())) {
			return false;
		}
		
		return true;
		
		
		
	}

	public List<Users> getAllUsers() {
		return usersRepo.findAll();
	}

}
