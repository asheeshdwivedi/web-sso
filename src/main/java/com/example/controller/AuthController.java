package com.example.controller;

import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;

/**
 * Created by asheeshdwivedi on 2/4/16.
 */
@Controller
public class AuthController {

    @RequestMapping("/user")
    @ResponseBody
    public Principal user(Principal user) {
        return user;
    }

}
