package com.fakeghost.bookshelf.ctrl;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Secure{
   @GetMapping("/login")
   public String login() {
      return "redirect:/secured";
   }
   @GetMapping("/")
   public String index() {
      return "index";
   }
   @GetMapping
   @ResponseBody
   public String index(ModelMap modelMap) {
      Authentication auth = SecurityContextHolder.getContext()
         .getAuthentication();
String username = null;
      if(auth != null 
            && auth.getPrincipal() != null
            && auth.getPrincipal() instanceof UserDetails) {
         username = ((UserDetails) auth.getPrincipal()).getUsername();
         modelMap.put("username", username);
            }
      return null == username? "No permitted": username ;
      //return "secure/index";
   }
}

