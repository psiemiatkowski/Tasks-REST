package com.crud.tasks.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
public class StaticWebPageController {

    @RequestMapping("/")
    public String index(Map<String, Object> model) {
        model.put("variable", "My Thymeleaf variable");
        model.put("firstMathText", "2 * 2 = ");
        model.put("secondMathText", "2 * 2 + 2 = ");
        model.put("thirdMathText", "2 - 2 * 2 = ");
        model.put("one", 1);
        model.put("two", 2);
        return "index";
    }
}
