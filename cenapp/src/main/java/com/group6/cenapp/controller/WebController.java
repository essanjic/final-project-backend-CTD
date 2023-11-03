package com.group6.cenapp.controller;

import com.group6.cenapp.services.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;



public class WebController {
    @Autowired
    private RestaurantService tableService;

    @RequestMapping(value = "/")
    public String index() {
        return "index";
    }

}

