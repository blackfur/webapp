package com.fakeghost.bookshelf.conf;

import org.jasig.cas.client.validation.Cas30ServiceTicketValidator;
import org.jasig.cas.client.validation.TicketValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.Primary;
import org.springframework.security.cas.ServiceProperties;
import org.springframework.security.cas.authentication.CasAuthenticationProvider;
import org.springframework.security.cas.web.CasAuthenticationEntryPoint;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.AuthenticationEntryPoint;

@Configuration
public class Cas{
   @Bean
   public ServiceProperties serviceProperties() {
      ServiceProperties serviceProperties = new ServiceProperties();
      serviceProperties.setService("http://localhost:9000/login/cas");
      serviceProperties.setSendRenew(false);
      return serviceProperties;
   }

   @Bean
   @Primary
   public AuthenticationEntryPoint authenticationEntryPoint(ServiceProperties sP) {
      CasAuthenticationEntryPoint entryPoint = new CasAuthenticationEntryPoint();
      entryPoint.setLoginUrl("https://localhost:8443/cas/login");
      entryPoint.setServiceProperties(sP);
      return entryPoint;
   }
   @Bean
   public TicketValidator ticketValidator() {
      return new Cas30ServiceTicketValidator(
            "https://localhost:8443/cas");
   }
   @Bean
   public CasAuthenticationProvider casAuthenticationProvider() {

      CasAuthenticationProvider provider = new CasAuthenticationProvider();
      provider.setServiceProperties(serviceProperties());
      provider.setTicketValidator(ticketValidator());
      provider.setUserDetailsService(
            s -> new User("casuser", "Mellon", true, true, true, true,
               AuthorityUtils.createAuthorityList("ROLE_ADMIN")));
      provider.setKey("CAS_PROVIDER_LOCALHOST_9000");
      return provider;
   }
}

