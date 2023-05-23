package com.online_school.controllers.tests;

import com.online_school.models.Person;
import com.online_school.services.PersonService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class TestController {


    @GetMapping("/tests")
    public String testsRun(Model model) {
        return "/test_html/test_html";
    }
}
