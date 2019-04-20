package com.fakeghost.bbs;

public static void main(String[] args) {     
   System.setProperty("server.servlet.context-path", "/api");
   SpringApplication.run(Application.class, args); 
}

