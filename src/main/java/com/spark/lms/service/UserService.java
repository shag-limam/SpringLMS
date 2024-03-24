package com.spark.lms.service;

import java.util.Date;
import java.util.List;

import com.spark.lms.model.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.spark.lms.model.User;
import com.spark.lms.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private IssueService issueService;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	public List<User> getAllUsers() {
		return userRepository.findAllByOrderByDisplayNameAsc();
	}
	
	
	public List<User> getAllActiveUsers() { return
		userRepository.findAllByActiveOrderByDisplayNameAsc(1);
	}
	
	public User getByUsername(String username) {
		return userRepository.findByUsername(username);
	}
	
	public User getById(Long id) {
		return userRepository.findById(id).get();
	}


	public User addNew(User user) {
		user.setPassword( passwordEncoder.encode(user.getPassword()) );
		user.setCreatedDate( new Date() );
		user.setLastModifiedDate( user.getCreatedDate() );
		user.setActive(1);
		return userRepository.save(user);
	}

	public User save(User user) {
		return userRepository.save( user );
	}
//	public User update(User user) {
//		user.setLastModifiedDate( new Date() );
//		return userRepository.save( user );
//	}
	
	public void delete(User user) {
		userRepository.delete(user);
	}
	
	public void delete(Long id) {
		userRepository.deleteById(id);
	}

	public boolean hasUsage(User user) {
		return issueService.getCountByUser(user) > 0;
	}


}
