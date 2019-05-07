package com.fakeghost.bbs;

import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class KaptchaTest extends MvcTestCase {
    @Test
    public void kaptcha_ShouldReturnJpg() throws Exception {

        MvcResult resp =  mockMvc.perform(get("/kaptcha.jpg")
        ).andExpect(status().isOk()) .andDo(print()) .andReturn();

        assertThat(resp.getResponse().getHeaderValue("Content-Type").toString(), containsString("image"));
    }
}

