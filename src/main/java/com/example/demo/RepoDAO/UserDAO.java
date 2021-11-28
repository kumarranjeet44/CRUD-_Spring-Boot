package com.example.demo.RepoDAO;

import java.util.ArrayList;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.Model.User;

public interface UserDAO extends JpaRepository<User,String> {
	
	
	@Query("from User where id=?1")
	public User findUserById(int id);
	
	@Query("from User")
	public ArrayList<User> findAllUsers();
	
	@Transactional
	@Modifying
	@Query("update User set uname=?1 where email=?2")
	public int updateUser(String uname,String email);
	

}
