package com.fakeghost.bbs;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

//import org.springframework.context.annotation.Import;

@Configuration
//the @AnnotationDrivenConfig needs to accompany the @Configuration annotation in order for the container to recognize the @Autowired annotation in other places of the code.
//@AnnotationDrivenConfig
//@Import({ WebConf.class })
@ImportResource("classpath:application.xml")
public class TestAppConf {
}
