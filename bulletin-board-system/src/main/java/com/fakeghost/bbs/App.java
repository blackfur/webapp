package com.fakeghost.bbs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@ComponentScan("com.fakeghost.bbs")
//@Configuration
//@EnableAutoConfiguration
//@Import({ AppConf.class, OtherConf.class })
public class App 
   //extends SpringBootServletInitializer
{
   public static void main(String[] args) {     
      //System.setProperty("server.servlet.context-path", "/api");
      SpringApplication.run(App.class, args); 
   }
}
