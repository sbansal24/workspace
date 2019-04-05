package com.test.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ControllerClass {

    @RequestMapping("/test-tomcat")
    public String getResponse(){
        return "tomcat test";
    }
}
