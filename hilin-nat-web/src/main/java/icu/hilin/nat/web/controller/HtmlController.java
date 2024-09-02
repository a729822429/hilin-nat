package icu.hilin.nat.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class HtmlController {

    @GetMapping
    public String index() {
        return "index";
    }

    @GetMapping("js/{path}")
    public String index(String path) {
        return "index";
    }

}
