package com.phamtranduc.bookStorePTD.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {
    @GetMapping("/admin")
    public String getAdmin(){
        return "admin";
    }

    @GetMapping("/template")
    public String getTemplate(){
        return "pages-blank";
    }
}
