package com.spark.lms.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.spark.lms.common.Constants;
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
	public List<User> getAll() {
		return userRepository.findAllByOrderByFirstNameAscUsernameAscDisplayNameAsc();
	}
	
	
	public List<User> getAllActiveUsers() { return
		userRepository.findAllByActiveOrderByDisplayNameAsc(1);
	}
	
	public User getByUsername(String username) {
		return userRepository.findByUsername(username);
	}



	//************** - **********
	public Long getTotalCount() {
		return userRepository.count();
	}

	public Long getUsersCount() {return userRepository.countByRole(Constants.ROLE_USER);}

	public Long getAdminsCount() {
		return userRepository.countByRole(Constants.ROLE_ADMIN);
	}

	//************** - ****************
	public User getById(Long id) {
		return userRepository.findById(id).get();
	}

	public User get(Long id) {
		return userRepository.findById(id).get();
	}

	public User addNew(User user) {
		user.setPassword( passwordEncoder.encode(user.getPassword()) );
		user.setCreatedDate( new Date() );
		user.setLastModifiedDate( user.getCreatedDate() );
		user.setActive(1);
		return userRepository.save(user);
	}

	public User addUser(User user) {
		user.setPassword( passwordEncoder.encode(user.getPassword()) );
		user.setCreatedDate( new Date() );
		user.setLastModifiedDate( user.getCreatedDate() );
		return userRepository.save(user);
	}

	public User save(User user) {
		user.setLastModifiedDate( new Date() );
		return userRepository.save( user );
	}
	public User update(User user) {
		user.setLastModifiedDate( new Date() );
		return userRepository.save( user );
	}

	public User addOrUpdateUser(User user) {
		// Vérifier si l'utilisateur existe déjà dans la base de données en fonction de son ID
		Optional<User> existingUserOptional = userRepository.findById(user.getId());
		if (existingUserOptional.isPresent()) {
			// L'utilisateur existe déjà dans la base de données, mettre à jour ses informations
			User existingUser = existingUserOptional.get();
			existingUser.setFirstName(user.getFirstName());
			existingUser.setDisplayName(user.getDisplayName());
			existingUser.setUsername(user.getUsername());
			existingUser.setPassword(passwordEncoder.encode(user.getPassword()));
			existingUser.setRole(user.getRole());
			existingUser.setActive(user.getActive());
			existingUser.setLastModifiedDate(new Date());
			// Enregistrer les modifications et renvoyer l'utilisateur mis à jour
			return userRepository.save(existingUser);
		} else {
			// L'utilisateur n'existe pas dans la base de données, ajoutez-le comme un nouvel utilisateur
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			user.setCreatedDate(new Date());
			user.setLastModifiedDate(user.getCreatedDate());
			user.setActive(1);
			return userRepository.save(user);
		}
	}


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
