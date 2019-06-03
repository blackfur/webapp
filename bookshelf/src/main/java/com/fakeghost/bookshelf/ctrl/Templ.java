package com.fakeghost.bookshelf.ctrl;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import com.fakeghost.bookshelf.model.Todo;
import java.util.List;
import java.util.ArrayList;

@Controller
public class Templ{
   @GetMapping("/welcome")                     // it will handle all request for /welcome
   public String welcome(ModelMap madelMap) {
      List<Todo> list= new ArrayList<>();
      list.add( new Todo(Long.valueOf(12), "Plant tree", false));
      list.add( new Todo(Long.valueOf(2), "Buy present", false));
      madelMap.put("list", list);
      return "welcome";           // welcome is view name. It will call welcome.jsp
   }
}
