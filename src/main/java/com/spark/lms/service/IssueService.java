package com.spark.lms.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spark.lms.common.Constants;
import com.spark.lms.model.Issue;
import com.spark.lms.model.Member;
import com.spark.lms.model.User;
import com.spark.lms.repository.IssueRepository;

@Service
public class IssueService {

	@Autowired
	private IssueRepository issueRepository;
	
	public List<Issue> getAll() {
		return issueRepository.findAll();
	}
	
	public Issue get(Long id) {
		return issueRepository.findById(id).get();
	}
	
	public List<Issue> getAllUnreturned() {
		return issueRepository.findByReturned( Constants.BOOK_NOT_RETURNED );
	}
	public List<Issue> getAllUnreturnedForCurrentUser(Long userId) {
		// Appeler la méthode du repository pour récupérer les problèmes non retournés de l'utilisateur spécifié
		return issueRepository.findByUserIdAndReturned(userId, Constants.BOOK_NOT_RETURNED);
	}
	public Issue addNew(Issue issue) {
		issue.setIssueDate( new Date() );
		issue.setReturned( Constants.BOOK_NOT_RETURNED );
		return issueRepository.save(issue);
	}
	
	public Issue save(Issue issue) {
		return issueRepository.save(issue);
	}
	
	public Long getCountByMember(Member member) {
		return issueRepository.countByMemberAndReturned(member, Constants.BOOK_NOT_RETURNED);
	}
	public Long getCountByUser(User user) {
		return issueRepository.countByUserAndReturned(user, Constants.BOOK_NOT_RETURNED);
	}
}
