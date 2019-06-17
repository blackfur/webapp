package com.fakeghost.bbs.ctrl;

import com.fakeghost.bbs.MvcTestCase;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class PostTest extends MvcTestCase{
    final Logger log = LoggerFactory.getLogger(getClass());

    @Test
    public void ShouldResturnAllComments() throws Exception{
        mockMvc.perform(get("/posts/comments"))
                .andExpect(status().isOk()) .andDo(print()) .andReturn();
    }
    @Test
    public void posts_ShouldList() throws Exception {

        //mockMvc.perform(get("/posts/" + id)).  andExpect(status().isOk()) .andDo(print()) .andReturn();

        mockMvc.perform(get("/posts").param("offset","0").param("limit","4"))
                .andExpect(status().isOk()) .andDo(print()) .andReturn();
        //assertThat(resp.getResponse().getContentAsString(), is("1.0"));
        mockMvc.perform(get("/posts/total").param("size","8"))
                .andExpect(status().isOk()) .andDo(print()) .andReturn();
    }
    @Test
    public void ShouldfetchOne() throws Exception {

        mockMvc.perform(get("/posts/timestamp/4096"))
                .andExpect(status().isOk()) .andDo(print()) .andReturn();
    }
    @Test
    public void SubmitShouldSuccess() throws Exception {

       MvcResult resp = mockMvc.perform(
                get("/posts/submit")
                .param("title","HTTP status code")
                .param("author","wiki leak")
                .param("createTime",System.currentTimeMillis()+"")
                .param("category","wiki")
                .param("content","An informational response indicates that the request was received and understood.")
            ) .andExpect(status().isOk()) .andDo(print()) 
         //.andExpect(content().string())
          .andReturn();
         String rstr = resp.getResponse().getContentAsString();
         ObjectMapper mapper = new ObjectMapper();
         Map<String, Object> m = mapper.readValue(rstr, Map.class);
         //mapper.writerWithDefaultPrettyPrinter().writeValueAsString(map);
         String timestamp= m.get("timestamp").toString();

    }

}
