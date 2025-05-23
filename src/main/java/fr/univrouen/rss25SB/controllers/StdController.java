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

    @GetMapping("/rss25SB/resume/html")
    public String resume(Model model) {
        List<Item> items = ItemRepository.findAll();
        model.addAttribute("items", items);
        return "resumeHtml";
    }
  
    @GetMapping("/help")
        public String help(Model model) {
        return "help";
    }
    

   
     // Aller vers la page de transfert
     @GetMapping("/transfert")
     public String transfert(){
         return "transfert";
     }

     @GetMapping("/rss25SB/html/{id}")
public String getItemById(@PathVariable Long id, Model model) {
    Optional<Item> itemOptional = ItemRepository.findById(id);

    if (itemOptional.isPresent()) {
        model.addAttribute("item", itemOptional.get());
        return "articleHtml";
    } else {
        model.addAttribute("id", id);
        model.addAttribute("status", "ERROR");
        return "item-error";
    }

    // Aller vers la page de conversion
    @GetMapping("/convert")
    public String convert(){
        return "convert";
    }
}


}

   

