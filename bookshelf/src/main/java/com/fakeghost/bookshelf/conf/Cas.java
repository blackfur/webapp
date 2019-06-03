package com.fakeghost.bookshelf.conf;

import org.jasig.cas.client.session.SingleSignOutFilter;
import org.jasig.cas.client.session.SingleSignOutHttpSessionListener;
import org.jasig.cas.client.validation.Cas30ServiceTicketValidator;
import org.jasig.cas.client.validation.TicketValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.cas.ServiceProperties;
import org.springframework.security.cas.authentication.CasAuthenticationProvider;
import org.springframework.security.cas.web.CasAuthenticationEntryPoint;
import org.springframework.security.cas.web.CasAuthenticationFilter;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;

import javax.servlet.http.HttpSessionEvent;
import java.util.Arrays;

@Configuration
@PropertySource("classpath:application.properties")
//@PropertySource("file:${app.home}/app.properties", classpath:db.properties)
//@Autowired
//Enviroment env;
//env.getProperty("mongodb.url")
//System.setProperty("app.home", "test");
//java -jar -Dapp.home="/home/mkyon/test" example.jar
public class Cas {

   @Value("${cas.serv.host}")
    String cas_serv_host;
   @Value("${cas.client.host}")
    String cas_client_host;

   @Bean
   public ServiceProperties serviceProperties() {
      ServiceProperties serviceProperties = new ServiceProperties();
      serviceProperties.setService(cas_client_host + "/login/cas");
      serviceProperties.setSendRenew(false);
      return serviceProperties;
   }

   @Bean
   @Primary
   public AuthenticationEntryPoint authenticationEntryPoint(ServiceProperties sP) {
      CasAuthenticationEntryPoint entryPoint = new CasAuthenticationEntryPoint();
      entryPoint.setLoginUrl(cas_serv_host + "/cas/login");
      entryPoint.setServiceProperties(sP);
      return entryPoint;
   }
   @Bean
   public TicketValidator ticketValidator() {
      return new Cas30ServiceTicketValidator(cas_serv_host + "/cas");
   }
   @Bean
   public CasAuthenticationProvider casAuthenticationProvider() {

      CasAuthenticationProvider provider = new CasAuthenticationProvider();
      provider.setServiceProperties(serviceProperties());
      provider.setTicketValidator(ticketValidator());
      /*
      provider.setUserDetailsService(
            s -> new User("casuser", "Mellon", true, true, true, true,
               AuthorityUtils.createAuthorityList("ROLE_ADMIN")));
      provider.setKey("CAS_PROVIDER_LOCALHOST_9000");
      */
      provider.setUserDetailsService((s) -> new User(
      "[email protected]", "testU",
      true, true, true, true,
    AuthorityUtils.createAuthorityList("ROLE_ADMIN")));
    provider.setKey("CAS_PROVIDER_LOCALHOST_9000");
      return provider;
   }
    @Bean
    public SecurityContextLogoutHandler securityContextLogoutHandler() {
       return new SecurityContextLogoutHandler();
    }

    @Bean
    public LogoutFilter logoutFilter() {
       LogoutFilter logoutFilter = new LogoutFilter(
             cas_serv_host + "/cas/logout", 
             securityContextLogoutHandler());
       logoutFilter.setFilterProcessesUrl("/logout/cas");
       return logoutFilter;
    }

    @Bean
    public SingleSignOutFilter singleSignOutFilter() {
       SingleSignOutFilter singleSignOutFilter = new SingleSignOutFilter();
       singleSignOutFilter.setCasServerUrlPrefix(cas_serv_host + "/cas");
       singleSignOutFilter.setIgnoreInitConfiguration(true);
       return singleSignOutFilter;
    }

    @EventListener
    public SingleSignOutHttpSessionListener singleSignOutHttpSessionListener(
          HttpSessionEvent event) {
       return new SingleSignOutHttpSessionListener();
          }
}

