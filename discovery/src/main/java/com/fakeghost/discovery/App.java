package com.fakeghost.discovery;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.boot.SpringApplication;

@SpringBootApplication
@EnableEurekaServer
public class App 
{
    public static void main( String[] args )
    {
		SpringApplication.run(App.class, args);
    }
}
