package com.spark.lms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spark.lms.common.Constants;
import com.spark.lms.model.Category;
import com.spark.lms.service.CategoryService;
import com.spark.lms.service.IssueService;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/issue")
public class IssueController {

	@Autowired
	private IssueService issueService;
	@Autowired
	private HttpSession httpSession;
	@Autowired
	private CategoryService categoryService;
	
	@ModelAttribute(name = "memberTypes")
	public List<String> memberTypes() {
		return Constants.MEMBER_TYPES;
	}

	@ModelAttribute(name = "userTypes")
	public List<String> userTypes() { return Constants.USERS_TYPES; }
	
	@ModelAttribute("categories")
	public List<Category> getCategories() {
		return categoryService.getAllBySort();
	}
	
	@RequestMapping(value = {"/", "/list"}, method = RequestMethod.GET)
	public String listIssuePage(Model model) {
		model.addAttribute("issues", issueService.getAllUnreturned());
		return "/issue/list";
	}

	@RequestMapping(value = {"/", "/listUser"})
	public String listIssuePageUser(Model model) {
		// Récupérer l'ID de l'utilisateur connecté à partir de la session
		Long userId = (Long) httpSession.getAttribute("idser");
		// Charger les problèmes non retournés spécifiques à cet utilisateur
		model.addAttribute("issues", issueService.getAllUnreturnedForCurrentUser(userId));
		return "/issue/listUser";
	}

//	@RequestMapping(value = {"/", "/listUser"}, method = RequestMethod.GET)
//	public String listIssuePageUser(Model model) {
//		model.addAttribute("issues", issueService.getAllUnreturned());
//		return "/issue/listUser";
//	}

	
	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public String newIssuePage(Model model) { 
		return "/issue/form";
	}

	@RequestMapping(value = "/newUser", method = RequestMethod.GET)
	public String newIssuePageUser(Model model) {
		return "/issue/formUser";
	}
	
}
