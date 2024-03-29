package com.spark.lms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spark.lms.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	public List<User> findAllByOrderByDisplayNameAsc();
	public List<User> findAllByActiveOrderByDisplayNameAsc(Integer active);
	//findAll ByOrderByFirstNameAsc MiddleNameAsc LastNameAsc
	public List<User> findAllByOrderByFirstNameAscUsernameAscDisplayNameAsc();
	public User findByUsername(String username);

	public Long countByRole(String role);
}
