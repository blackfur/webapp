package com.fakeghost.bbs.ctrl;

import com.fakeghost.bbs.MvcTestCase;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class PostTest extends MvcTestCase{
    @Test
    public void posts_ShouldList() throws Exception {
        final Logger log = LoggerFactory.getLogger(getClass());

       mockMvc.perform(
                get("/posts/submit")
                .param("title","HTTP status code")
                .param("author","wiki leak")
                .param("createTime",System.currentTimeMillis()+"")
                .param("category","wiki")
                .param("content","An informational response indicates that the request was received and understood.")
            ) .andExpect(status().isOk()) .andDo(print()) .andReturn();

        mockMvc.perform(get("/posts").param("offset","0").param("limit","4"))
                .andExpect(status().isOk()) .andDo(print()) .andReturn();
        //assertThat(resp.getResponse().getContentAsString(), is("1.0"));
    }

}
