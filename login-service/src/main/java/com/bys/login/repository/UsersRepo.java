package com.bys.login.repository;


import com.bys.login.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface UsersRepo extends JpaRepository<Users, String> {
	
	

}
