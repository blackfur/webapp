package com.fakeghost.zuul;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.netflix.zuul.ZuulFilter;

public class RouteFilter extends ZuulFilter {
   final Logger log = LoggerFactory.getLogger(getClass());

	@Override
	public String filterType() {
		return "error";
	}

	@Override
	public int filterOrder() {
		return 0;
	}

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() {
		log.info("Using route Filter");

		return null;
	}

}
