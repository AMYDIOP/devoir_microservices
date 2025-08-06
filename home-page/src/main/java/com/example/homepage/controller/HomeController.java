package com.example.homepage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("categoriesUrl", "http://localhost:8090/api/categories");
        model.addAttribute("produitsUrl", "http://localhost:8090/api/v1/produits");
        model.addAttribute("usersUrl", "http://localhost:8090/api/v1/users");
        model.addAttribute("commandesUrl", "http://localhost:8090/api/commandes");

        return "index"; // correspond à templates/index.html
    }
}
