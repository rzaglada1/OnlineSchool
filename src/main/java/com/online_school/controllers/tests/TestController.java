package com.online_school.controllers.tests;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {


    @GetMapping("/tests")
    public String testsRun(Model model) {
        return "/test_html/test_html";
    }
}
