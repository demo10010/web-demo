package com.haizhi.webdemo.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/")
public class IndexPageController {

    @GetMapping("/")
    public ModelAndView index() {
        return new ModelAndView("doc.html#/home");
    }

}
