package com.tstore.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

      @RequestMapping("/index")
     public String index(){
           return "first message system";
     }

}
