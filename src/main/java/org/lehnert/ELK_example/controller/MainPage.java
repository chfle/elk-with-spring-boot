package org.lehnert.ELK_example.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MainPage {
    private static final Logger log = LoggerFactory.getLogger(MainPage.class);

    @GetMapping
    String getMainPage() {
        log.info("get Main Page");
        return "main";
    }
}
