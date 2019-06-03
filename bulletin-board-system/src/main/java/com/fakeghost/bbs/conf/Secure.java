package com.fakeghost.bbs.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class Secure extends WebSecurityConfigurerAdapter {

   @Autowired
   DataSource dataSource;

    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
       /*
        auth.inMemoryAuthentication()
          //.withUser("user").password(passwordEncoder().encode("1")).roles("USER")
          //.and()
          .withUser("admin").password("1").roles("ADMIN");
          */
      auth.jdbcAuthentication().dataSource(dataSource);
            //.usersByUsernameQuery( "select username, password from individual where username=?")
            //.authoritiesByUsernameQuery( "select username, role from individual where username=?");
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http
           .cors().and()
          .csrf().disable()
          .authorizeRequests()
          .antMatchers("/secured/**").hasAuthority("ADMIN")
                //.hasRole("ADMIN")
          /*
          .antMatchers("/anonymous*").anonymous()
          .antMatchers("/login*").permitAll()
          .anyRequest().authenticated()
          */
          .anyRequest().permitAll()
          .and()
          .formLogin()
          //.loginPage("/login.html")
          //.loginProcessingUrl("/loginProcessing")
          //.defaultSuccessUrl("/home.html", true)
          //.failureUrl("/login.html?error=true")
          //.failureHandler()
          .and()
          .logout()
          //.logoutUrl("/logout")
          .deleteCookies("JSESSIONID");
          //.logoutSuccessHandler(logoutSuccessHandler());
    }
    
    /*
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    */
}
