package com.fakeghost.bookshelf;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = TestCtrl.class)
public class WebContentTest {
   final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void indexhtml() throws Exception {
        mockMvc.perform(get("/index.html")) .andExpect(content().string(containsString("html6")));
    }

    @Test
    public void register() throws Exception {
        mockMvc.perform(get("/register")) .andExpect(content().string(containsString("emp1")));
    }

    /*
    @Test
    public void greetingWithUser() throws Exception {
        //mockMvc.perform(get("/index").param("name", "Greg")) .andExpect(content().string(containsString("Hello, Greg!")));
    }
     * */
}
