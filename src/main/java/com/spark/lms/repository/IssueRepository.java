package com.spark.lms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spark.lms.model.Issue;
import com.spark.lms.model.Member;
import com.spark.lms.model.User;


@Repository
public interface IssueRepository extends JpaRepository<Issue, Long> {
	public List<Issue> findByReturned(Integer returned);
	public Long countByMemberAndReturned(Member member, Integer returned);
	public Long countByUserAndReturned(User user, Integer returned);
	List<Issue> findByUserIdAndReturned(Long userId, Integer returned);
}
