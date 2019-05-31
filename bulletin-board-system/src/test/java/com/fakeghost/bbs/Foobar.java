package com.fakeghost.bbs;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {App.class}, webEnvironment=WebEnvironment.RANDOM_PORT)
@ActiveProfiles("dev")
public class Foobar {
    @Autowired
    protected WebApplicationContext wac;

    @Test
    public void shouldGetRabbitmqMsg() throws Exception {
		 assertTrue(true);
    }
}

