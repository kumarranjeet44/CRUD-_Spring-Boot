package com.example.demo.Controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.Model.User;
import com.example.demo.RepoDAO.UserDAO;

@Controller
public class MyController {
	
	@Autowired
	UserDAO dao;
		
	@RequestMapping("/register")//perform register operation using post method
	public String register(User u) {
		dao.save(u);
		return "responsePage";
	}
	
	@RequestMapping("/search")//perform read operation using get method
	public String search(int id) {//here we are returning the list of user with same name;
		User u=dao.findUserById(id);
		if(u!=null) {
			return "responsePage";
		}
		return "error";		
	}
	
	@RequestMapping("/findAll")
		public String findAllUser(Model m) {
	     List<User> u=dao.findAllUsers();
	     if(u.size()!=0) {
	    	 m.addAttribute("Users", u);
				return "usersList";
	     }
	    return "error";
		}
	
	@RequestMapping("/update")//update the information present in server using put method
	public String update(int id,String uname,String email) {
		
		User u=dao.findUserById(id);
		if(u!=null) {
		//u.setUname(uname); we cannot do update the database,becaues using this only object content will change not database 
		//u.setEmail(email);-->this step will not update the information in database;			
		//use transactional query to update the database;
			dao.updateUser(uname,email);
			
			return "responsePage";
		}
		return "error";
			
	}
	
	@RequestMapping("/delete")//delete the user by id using delete method
	public String delete(int id) {
		User u=dao.findUserById(id);
		if(u!=null) {
			dao.delete(u);
			return "responsePage";
		}
		return "error";
		
	}
	

}
