package com.fakeghost.consumer;

import org.springframework.test.context.junit4.SpringRunner;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.junit.Test;
import java.util.List;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@RunWith(SpringRunner.class)
/*
@SpringBootTest(
  //SpringBootTest.WebEnvironment.MOCK,
  classes = App.class)
@TestPropertySource(
  locations = "classpath:bootstrap.yml")
  */
public class CtrlClientTest{
   final Logger log = LoggerFactory.getLogger(getClass());
   /*
   @Autowired
   private DiscoveryClient discoveryClient;
   @Test
   public void baseUrl(){
      List<ServiceInstance> instances=discoveryClient.getInstances("producer");
		ServiceInstance serviceInstance=instances.get(0);
		String baseUrl=serviceInstance.getUri().toString();
      log.info(baseUrl);
   }
   */
   @Test
   public void sample(){
      log.info("Nothing");
   }
}
