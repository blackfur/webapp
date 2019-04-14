package com.fakeghost.consumer;

import java.io.IOException;
import org.springframework.web.client.RestClientException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class ConsumerCtrlClient{

   final Logger log = LoggerFactory.getLogger(getClass());

   @Autowired
	private RemoteCallService serv;

	public void getEmployee() throws RestClientException, IOException {

		try{
         log.info(serv.getData());
		}catch (Exception ex)
		{
         log.error("Request failed: ",ex);
		}
	}

}
