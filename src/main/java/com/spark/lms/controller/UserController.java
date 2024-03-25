package com.spark.lms.controller;

import java.util.List;

import javax.validation.Valid;

//import org.springframework.validation.BindingResult;

import com.spark.lms.model.Member;
import com.spark.lms.repository.UserRepository;
import com.spark.lms.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spark.lms.common.Constants;
import com.spark.lms.model.User;
import com.spark.lms.service.UserService;

@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;
    @ModelAttribute(name = "userTypes")
    public List<String> userTypes() {
        return Constants.USERS_TYPES;
    }

    @ModelAttribute(name = "userActive")
    public List<Long> userActive() {
        return Constants.USERS_ACTIV;
    }

    @RequestMapping(value = {"/", "/list"},  method = RequestMethod.GET)
    public String showUsersPage(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "/user/list";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addUserPage(Model model) {
        model.addAttribute("user", new User());
        return "/user/form";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editUserPage(@PathVariable(name = "id") Long id, Model model) {
        User user = userService.getById( id );
        if( user != null ) {
            model.addAttribute("user", user);
            return "/user/form";
        } else {
            return "redirect:/user/add";
        }
    }



//
//    @RequestMapping(value = "/save", method = RequestMethod.POST)
//    public String saveUser(@Valid User user, BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
//        if (bindingResult.hasErrors()) {
//            // Il y a des erreurs de validation
//            for (FieldError error : bindingResult.getFieldErrors()) {
//                // Imprimez chaque erreur dans la console
//                System.out.println("Error in field " + error.getField() + ": " + error.getDefaultMessage());
//            }
//            // Redirigez vers le formulaire utilisateur avec les erreurs
//            return "user/form"; // Assurez-vous que le chemin est correct, sans le slash initial
//        }
//
//        // Aucune erreur de validation, enregistrez ou mettez à jour l'utilisateur
//        if (user.getId() == null) {
//            userService.addNew(user);
//            redirectAttributes.addFlashAttribute("successMsg", "'" + user.getFirstName() + " " + user.getDisplayName() + "' is added as a new user.");
//            return "redirect:/user/add"; // Redirigez vers la page d'ajout d'utilisateur après l'ajout réussi
//        } else {
//            User updatedUser = userService.save(user);
//            redirectAttributes.addFlashAttribute("successMsg", "Changes for '" + user.getFirstName() + " " + user.getDisplayName() + "' are saved successfully.");
//            return "redirect:/user/edit/" + updatedUser.getId(); // Redirigez vers la page d'édition d'utilisateur après la mise à jour réussie
//        }
//    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveUser(@Valid User user, BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            // Il y a des erreurs de validation
            for (FieldError error : bindingResult.getFieldErrors()) {
                // Imprimez chaque erreur dans la console
                System.out.println("Error in field " + error.getField() + ": " + error.getDefaultMessage());
            }
            return "user/form"; // Assurez-vous que le chemin est correct, sans le slash initial
        }


        if (user.getId() != null) {
            User updatedUser = userService.addOrUpdateUser(user);
            redirectAttributes.addFlashAttribute("successMsg", "Changes for '" + user.getFirstName() + " " + user.getDisplayName() + "' are saved successfully.");
            return "redirect:/user/edit/" + updatedUser.getId(); // Redirigez vers la page d'édition d'utilisateur après la mise à jour réussie
        }
        else {
            userService.addUser(user);
            redirectAttributes.addFlashAttribute("successMsg", "'" + user.getFirstName() + " " + user.getDisplayName() + "' is added as a new user.");
            return "redirect:/user/add"; // Redirigez vers la page d'ajout d'utilisateur après l'ajout réussi


        }


    }


    @RequestMapping(value = "/savepp", method = RequestMethod.POST)
    public String saveUser1(@Valid User user, BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if( bindingResult.hasErrors() ) {
            for (ObjectError error : bindingResult.getAllErrors()) {
                System.out.println("Error: " + error.getDefaultMessage());
            }
            return "/user/form";
        }

        if( user.getId() == null ) {

            //user.setRole(Constants.ROLE_USER);
            userService.addNew(user);
            redirectAttributes.addFlashAttribute("successMsg", "'" + user.getFirstName()+" "+user.getDisplayName() + "' is added as a new member.");
            for (ObjectError error : bindingResult.getAllErrors()) {
                System.out.println("Error: " + error.getDefaultMessage());
            }
            return "redirect:/user/add";
        } else {
            User updatedUser = userService.save( user );
            //User updatedUser = userRepository.save( user );
            redirectAttributes.addFlashAttribute("successMsg", "Changes for '" + user.getFirstName()+" "+user.getDisplayName() + "' are saved successfully. ");
            return "redirect:/user/edit/" + updatedUser.getId();
        }
    }

    @RequestMapping(value = "/remove/{id}", method = RequestMethod.GET)
    public String removeUser(@PathVariable(name = "id") Long id, Model model) {
        User user = userService.getById( id );
        if( user != null ) {
            if( userService.hasUsage(user) ) {
                model.addAttribute("userInUse", true);
                return showUsersPage(model);
            } else {
                userService.delete(id);
            }
        }
        return "redirect:/user/list";
    }
}
