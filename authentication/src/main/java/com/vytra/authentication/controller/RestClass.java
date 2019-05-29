package com.vytra.authentication.controller;

import com.vytra.authentication.util.Util;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping(value = "/user")
public class RestClass {
   /* @Value("${app.id}")
    String instance;*/

    @GetMapping("/")
    public String hola() {
        String username;

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }
        Util.userAuth();
        return username;
    }

    @GetMapping("/current")
    public Principal getUser(Principal principal) {

        return principal;
    }

}