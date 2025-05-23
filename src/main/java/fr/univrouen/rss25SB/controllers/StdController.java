package fr.univrouen.rss25SB.controllers;

import fr.univrouen.rss25SB.entity.Item;
import fr.univrouen.rss25SB.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

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

    @GetMapping("/rss25SB/html/{id}")
    public String article(Model model, @PathVariable Long id) {
        Optional<Item> optionalItem = ItemRepository.findById(id);
        if (optionalItem.isPresent()) {
            model.addAttribute("item", optionalItem.get());
            return "articleHtml";
        } else {
            return "itemNotFound"; // Crée une vue "itemNotFound.html" pour gérer l'erreur proprement
        }
    }
        return "resumeHtml";
    }

    // Aller vers la page de transfert
    @GetMapping("/transfert")
    public String transfert(){
        return "transfert";
    }
}
