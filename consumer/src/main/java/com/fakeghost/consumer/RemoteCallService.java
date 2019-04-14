package com.fakeghost.consumer;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name="producer")
public interface RemoteCallService {
	@RequestMapping(method=RequestMethod.GET, value="/employee")
	public String getData();

}
