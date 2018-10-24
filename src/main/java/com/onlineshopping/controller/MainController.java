package com.onlineshopping.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
    
    @RequestMapping("/")
    public String gotoIndex() {
	
	return "index";
    }
    
    @RequestMapping("/pageNotFound")
    public String pageNotFound() {
	
	return "notfound-404";
    }
}
