package com.fakeghost.bbs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.fakeghost.bbs")
public class App 
   //extends SpringBootServletInitializer
{
   public static void main(String[] args) {     
      //System.setProperty("server.servlet.context-path", "/api");
      SpringApplication.run(App.class, args); 
   }
}
