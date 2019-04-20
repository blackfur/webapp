package com.fakeghost.bbs.conf;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource("classpath:spring.xml")
//@ImportResource(value="classpath:context.properties", reader=PropertiesBeanDefinitionReader.class)
public class AppConf {

}

