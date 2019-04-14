package com.fakeghost.consumer;

import java.io.IOException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;

public class ConsumerCtrlClient{

   final Logger log = LoggerFactory.getLogger(getClass());

   @Autowired
   //private DiscoveryClient discoveryClient;
	private LoadBalancerClient loadBalancer;

	public void getEmployee() throws RestClientException, IOException {

		//String baseUrl = "http://localhost:8080/employee";
      //List<ServiceInstance> instances=discoveryClient.getInstances("producer");
		//ServiceInstance serviceInstance=instances.get(0);
      ServiceInstance serviceInstance=loadBalancer.choose("producer");
		String baseUrl=serviceInstance.getUri().toString();
      log.info(baseUrl);

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response=null;
		try{
         response=restTemplate.exchange(baseUrl+"/employee", HttpMethod.GET, getHeaders(),String.class);
		}catch (Exception ex)
		{
         log.error("Request failed: ",ex);
		}
		log.info(response.getBody());
	}

	private static HttpEntity<?> getHeaders() throws IOException {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		return new HttpEntity<>(headers);
	}
}
