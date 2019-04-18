package com.fakeghost.bbs.ctrl;

import com.fakeghost.bbs.MvcTestCase;
import com.fakeghost.bbs.model.UserMapper;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class LoginTest extends MvcTestCase {

   @InjectMocks
   @Autowired
   @Qualifier("userMapper")
   UserMapper userMapper;

   @Test
   public void login_ShouldRedirect2mainJsp() throws Exception{
      userMapper.register(2, "Albert","1234.abcd");
      //String password = URLEncoder.encode("1234.abcd", "utf-8");
      //when(userMapper.user("Albert","1234.abcd")).thenReturn(new User(2,"Albert","1234.abcd"));
      mockMvc.perform(post("/LoginServlet/")
         .contentType(MediaType.APPLICATION_FORM_URLENCODED)
              //.content("username=Albert&password="+password)
              .param("username","Albert")
              .param("password","1234.abcd")
      ).andExpect(status().is3xxRedirection())
              //.andExpect(view().name("main"))
              //.andExpect(forwardedUrl("/main.jsp"));
      .andExpect(redirectedUrl("/main.jsp"));
      //verify(userMapper, times(1)).user("Albert","1234.abcd");
      //verifyNoMoreInteractions(userMapper);
   }
}
