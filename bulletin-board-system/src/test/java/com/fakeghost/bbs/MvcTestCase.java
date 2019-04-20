package com.fakeghost.bbs;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

//@RunWith(SpringJUnit4ClassRunner.class)
@RunWith(SpringRunner.class)
//@ContextConfiguration({"classpath:spring.xml"})
//@ContextConfiguration(classes = {AppConf.class})
//@WebAppConfiguration(value= "src/main/webapp")
@SpringBootTest(classes = {AppConf.class}, webEnvironment=WebEnvironment.RANDOM_PORT)
public abstract class MvcTestCase {
    @Autowired
    protected WebApplicationContext wac;

    protected MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        /*
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/templates/");
        viewResolver.setSuffix(".ftl");
         */
        mockMvc = MockMvcBuilders.webAppContextSetup(wac)
                //.setViewResolvers(viewResolver)
        .dispatchOptions(true)
                .alwaysDo(print())
                .build();
        MockitoAnnotations.initMocks(this);
    }
}
