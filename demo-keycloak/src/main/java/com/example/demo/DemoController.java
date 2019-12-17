package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/demo")
public class DemoController {
    @RequestMapping(value = "/test")
    @ResponseBody
    public boolean test(){
        return true;
    }
}
