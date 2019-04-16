package com.fakeghost.bookshelf;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
//import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
public class TestCtrl{

	@RequestMapping(value = "/register", method = RequestMethod.GET)
   //@HystrixCommand(fallbackMethod = "getDataFallBack")
	public Employee register() {

		Employee emp = new Employee();
		emp.setName("emp1");
		emp.setDesignation("manager");
		emp.setEmpId("1");
		emp.setSalary(3000);

      /*
      if(emp.getName().equalsIgnoreCase("emp1"))
         throw new RuntimeException();
         */

		return emp;
	}

   public Employee getDataFallBack() {
		
		Employee emp = new Employee();
		emp.setName("fallback-emp1");
		emp.setDesignation("fallback-manager");
		emp.setEmpId("fallback-1");
		emp.setSalary(3000);

		return emp;
		
	}

}
