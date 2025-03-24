package org.lambton.csd_4464.week12.controller;

import org.lambton.csd_4464.week12.model.SampleModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String index(Model model) {
        SampleModel sample = new SampleModel(1L, "Hello from Lombok!");
        model.addAttribute("sample", sample);
        return "index";
    }
}
