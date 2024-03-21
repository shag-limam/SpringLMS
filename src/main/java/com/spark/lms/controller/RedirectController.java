//package com.spark.lms.controller;
//
//import com.spark.lms.common.Constants;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//@Controller
//public class RedirectController {
//
//    @GetMapping("/defaultRedirect")
//    public String defaultRedirect(HttpServletRequest request, HttpServletResponse response) {
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        String redirectUrl = "/";
//        if (auth != null && auth.getAuthorities() != null) {
//            for (GrantedAuthority authority : auth.getAuthorities()) {
//                if (authority.getAuthority().equals(Constants.ROLE_USER)) {
//                    redirectUrl = "/book/listUserUI";
//                } else if (authority.getAuthority().equals(Constants.ROLE_ADMIN)) {
//                    redirectUrl = "/";
//                }
//                // Add more conditions for other roles if needed
//            }
//        }
//        return "redirect:" + redirectUrl;
//    }
//}
