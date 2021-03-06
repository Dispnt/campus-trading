package com.dispnt.mall.controller;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
public class WebRestController {

    @GetMapping("/hello")
    public String hello(){
        return "HelloWorld!";
    }


}
