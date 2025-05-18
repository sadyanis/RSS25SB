package fr.univrouen.rss25SB.controllers;

import fr.univrouen.rss25SB.entity.Item;
import fr.univrouen.rss25SB.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

import java.util.List;

@Controller
public class StdController {
@Autowired
private ItemRepository ItemRepository;
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
    List<Item> items = ItemRepository.findAll();
    model.addAttribute("items", items);

    return "resumeHtml";
}
}
