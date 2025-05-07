package fr.univrouen.rss25SB.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

@Controller
public class StdController {

@GetMapping("/")
    public String index(Model model) {
    model.addAttribute("title", "Accueil");
    return "index";
}
@GetMapping("/help")
    public String help(Model model) {
    return "help";
}
@GetMapping("/rss25SB/resume/html")
    public String resume(Model model) {

    return "resume";
}
}
