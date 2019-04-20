package com.fakeghost.bbs;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.core.io.ClassPathResource;
import com.google.code.kaptcha.servlet.KaptchaServlet;

@Configuration
@EnableCaching
@ImportResource("classpath:spring.xml")
//@ImportResource(value="classpath:context.properties", reader=PropertiesBeanDefinitionReader.class)
public class AppConf {

   @Bean
	public CacheManager cacheManager() {
		return new EhCacheCacheManager(ehCacheCacheManager().getObject());
	}
   @Bean
	public EhCacheManagerFactoryBean ehCacheCacheManager() {
		EhCacheManagerFactoryBean cmfb = new EhCacheManagerFactoryBean();
		cmfb.setConfigLocation(new ClassPathResource("ehcache.xml"));
		cmfb.setShared(true);
		return cmfb;
	}
   @Bean
   public ServletRegistrationBean kaptcha(){
      ServletRegistrationBean kaptchaServ = new ServletRegistrationBean(new KaptchaServlet(), "/kaptcha.jpg");
      kaptchaServ.addInitParameter("kaptcha.image.width", "128");
      kaptchaServ.addInitParameter("kaptcha.image.height", "64");
      kaptchaServ.addInitParameter("kaptcha.textproducer.char.length", "4");
      kaptchaServ.addInitParameter("kaptcha.noise.impl", "com.google.code.kaptcha.impl.NoNoise");
      kaptchaServ.addInitParameter("kaptcha.session.key", "kcode");
      return kaptchaServ;
   }
}

