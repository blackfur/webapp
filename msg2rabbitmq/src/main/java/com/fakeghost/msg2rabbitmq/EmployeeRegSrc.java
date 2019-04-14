package com.fakeghost.msg2rabbitmq;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface EmployeeRegSrc {

	@Output("employeeRegistrationChannel")
	MessageChannel employeeRegistration();

}
