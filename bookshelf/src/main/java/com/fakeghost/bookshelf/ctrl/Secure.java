package com.fakeghost.bookshelf.ctrl;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.CookieClearingLogoutHandler;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.authentication.rememberme.AbstractRememberMeServices;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class Secure{
   @GetMapping("/login")
   public String login() {
      return "redirect:/secured";
   }
   @GetMapping("/")
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

   @GetMapping("/logout")
   public String logout(
         HttpServletRequest request,
         HttpServletResponse response,
         SecurityContextLogoutHandler logoutHandler) {
      Authentication auth = SecurityContextHolder
         .getContext().getAuthentication();
      logoutHandler.logout(request, response, auth );
      new CookieClearingLogoutHandler(
            AbstractRememberMeServices.SPRING_SECURITY_REMEMBER_ME_COOKIE_KEY)
         .logout(request, response, auth);
      return "auth/logout";
         }
}

