package com.fakeghost.consumer;

import java.io.IOException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import java.util.List;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

public class ConsumerCtrlClient{

   final Logger log = LoggerFactory.getLogger(getClass());

   @Autowired
   private DiscoveryClient discoveryClient;

   public void getEmployee() throws IOException, RestClientException {

      List<ServiceInstance> instances = discoveryClient.getInstances("zuul");
      if(instances.size() == 0){
         //throw new Exception("No ZUUL instance found.");
         String err = "No ZUUL instance found.";
        log.info(err); 
         return;
      }
      ServiceInstance serviceInstance = instances.get(0);

      String baseUrl = serviceInstance.getUri().toString();

      RestTemplate restTemplate = new RestTemplate();
      ResponseEntity<String> response = null;
      response = restTemplate.exchange(baseUrl+"/producer/employee", HttpMethod.GET, getHeaders(), String.class);
      log.info(response.getBody());
   }

   private static HttpEntity<?> getHeaders() throws IOException {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		return new HttpEntity<>(headers);
	}

}
