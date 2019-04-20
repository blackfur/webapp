package com.fakeghost.bbs.conf;

import com.google.code.kaptcha.servlet.KaptchaServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Kaptcha {
    @Bean
    public ServletRegistrationBean kaptchaServlet(){
        ServletRegistrationBean kaptchaServ = new ServletRegistrationBean(new KaptchaServlet(), "/kaptcha.jpg");
        kaptchaServ.addInitParameter("kaptcha.image.width", "128");
        kaptchaServ.addInitParameter("kaptcha.image.height", "64");
        kaptchaServ.addInitParameter("kaptcha.textproducer.char.length", "4");
        kaptchaServ.addInitParameter("kaptcha.noise.impl", "com.google.code.kaptcha.impl.NoNoise");
        kaptchaServ.addInitParameter("kaptcha.session.key", "kcode");
        return kaptchaServ;
    }
}
