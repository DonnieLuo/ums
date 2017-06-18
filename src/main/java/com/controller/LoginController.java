package com.controller;

/**
 * Created by Donnie on 2017/6/18.
 */

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
public class LoginController{

    @RequestMapping("/login")
    public String login() {
        return "login";
    }
    @RequestMapping("/")
    public String home() {
        return "home";
    }

}
