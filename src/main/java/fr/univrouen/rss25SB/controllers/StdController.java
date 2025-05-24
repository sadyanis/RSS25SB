package fr.univrouen.rss25SB.controllers;

import fr.univrouen.rss25SB.Services.Rss25Service;
import fr.univrouen.rss25SB.entity.Item;
import fr.univrouen.rss25SB.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
public class StdController {

    @Autowired
    private Rss25Service rss25Service;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("title", "Accueil");
        return "index";
    }

    @GetMapping("/rss25SB/resume/html")
    public String resume(Model model) {
        List<Item> items = rss25Service.getAllItems();
        List<Item> valids = items.stream()
                .filter(item -> item.getGuid() != null )
                .toList();

        model.addAttribute("items", valids);
        return "resumeHtml";
    }

    @GetMapping("/help")
    public String getHelp(Model model) {
        List<Map<String, String>> operations = List.of(
                Map.of(
                        "url","/",
                        "method", "GET",
                        "summary", "Affiche la Page d'accueil du site"
                ),
                Map.of(
                        "url","/help",
                        "method","GET",
                        "summary","Affiche la liste des opérations gérées par le service REST"
                ),
                Map.of(
                        "url", "/rss25SB/resume/html",
                        "method", "GET",
                        "summary", "Affiche la liste des articles stockés  au format HTML"
                ),
                Map.of(
                        "url", "/rss25SB/resume/xml",
                        "method", "GET",
                        "summary", "Retourne les résumés des articles stockés au format XML"
                ),
                Map.of(
                        "url", "/rss25SB/html/{id}",
                        "method", "GET",
                        "summary", "Retourne le détail d’un article  dont l’identifiant est {id} au format HTML"
                ),
                Map.of(
                        "url", "/rss25SB/resume/xml/{id}",
                        "method", "GET",
                        "summary", "Affiche le contenu complet de l’article dont l’identifiant est {id} au format XML"
                ),
                Map.of(
                        "url", "/rss25SB/insert",
                        "method", "POST",
                        "summary", "Insère un nouvel article  (XML attendu)"
                ),
                Map.of(
                        "url", "/transfert",
                        "method", "GET",
                        "summary", "Formulaire pour transférer un fichier XML"
                ),
                Map.of(
                        "url", "/convert",
                        "method", "GET",
                        "summary", "Permet de convertir un fichier XML en HTML ou en résumé"
                )
        );

        model.addAttribute("operations", operations);
        return "help";
    }
    

   
     // Aller vers la page de transfert
     @GetMapping("/transfert")
     public String transfert(){
         return "transfert";
     }

     @GetMapping("/rss25SB/html/{id}")
public String getItemById(@PathVariable Long id, Model model) {
    Optional<Item> itemOptional = rss25Service.getItemById(id);

    if (itemOptional.isPresent()) {
        model.addAttribute("item", itemOptional.get());
        return "articleHtml";
    } else {
        model.addAttribute("id", id);
        model.addAttribute("status", "ERROR");
        return "item-error";
    }

    // Aller vers la page de conversion
   
}
@GetMapping("/convert")
public String convert(){
    return "convert";
}

}

   

