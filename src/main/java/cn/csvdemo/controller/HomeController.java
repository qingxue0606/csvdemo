 package cn.csvdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author xiang
 * @date 2018/12/12
 */
 @Controller
public class HomeController {
     
     @RequestMapping("/")
     @ResponseBody
     public String hello() {
         return "ok";
     }

}
