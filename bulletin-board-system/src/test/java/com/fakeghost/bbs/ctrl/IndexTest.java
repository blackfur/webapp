package com.fakeghost.bbs.ctrl;

import com.fakeghost.bbs.MvcTestCase;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class IndexTest extends MvcTestCase {
    @Test
    public void index_ShouldReturnString() throws Exception {

       /*
        *
    <servlet-mapping>
        <servlet-name>SpringDispatcher</servlet-name>
        <url-pattern>/api/*</url-pattern>
    </servlet-mapping>
    Don't add Url prefix(/api/) When Unit test!
        * */
        MvcResult resp =  mockMvc.perform(get("/index/license")
        ).andExpect(status().isOk()) .andDo(print()) .andReturn();
        assertThat(resp.getResponse().getContentAsString(), is("1.0"));
    }
}
