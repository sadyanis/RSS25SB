package fr.univrouen.rss25SB.controllers;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.List;
import java.util.Optional;


import fr.univrouen.rss25SB.Services.ItemConversionService;
import fr.univrouen.rss25SB.Services.Rss25Service;
import fr.univrouen.rss25SB.model.ItemSummary;
import fr.univrouen.rss25SB.model.ItemsSummaryJAXB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.univrouen.rss25SB.entity.Item;
import fr.univrouen.rss25SB.model.ItemJAXB;
import fr.univrouen.rss25SB.repository.ItemRepository;
import fr.univrouen.rss25SB.utils.Utils;



@RestController
public class GetController {

    @Autowired
    ItemRepository itemRepository;
    @Autowired
    Rss25Service rss25SBService;

    @GetMapping("/resume")
    public String getListRssinXML(){
        return "Envoi de la liste des flux RSS";
    }
    // @GetMapping("/guid")
    // public String getRSSinXML(@RequestParam(value= "guid") String texte ){
    //     return "Détail du contenu RSS demandé " + texte ;
    // }
    @GetMapping("/test")
    public String test(@RequestParam(value = "nb") Integer nb , @RequestParam(value = "search") String texte ){
        return  "Test : \n guid = "+nb+"\n search = "+texte;
    }




    }
