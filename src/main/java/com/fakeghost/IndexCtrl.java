package com.fakeghost;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/index")
public class IndexCtrl {
    @RequestMapping("/license")
    @ResponseBody
    public String license(){
        return "1.0";
    }
    @RequestMapping(value="/", method = RequestMethod.GET)
    public String index(){
        return "index";
    }
    @RequestMapping("/foo")
    @ResponseBody
    public String foo() {
        return "1299";
    }
}
