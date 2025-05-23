package fr.univrouen.rss25SB.controllers;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.Optional;


import fr.univrouen.rss25SB.Services.ItemConversionService;
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
    

    @GetMapping("/resume")
    public String getListRssinXML(){
        return "Envoi de la liste des flux RSS";
    }
    @GetMapping("/guid")
    public String getRSSinXML(@RequestParam(value= "guid") String texte ){
        return "Détail du contenu RSS demandé " + texte ;
    }
    @GetMapping("/test")
    public String test(@RequestParam(value = "nb") Integer nb , @RequestParam(value = "search") String texte ){
        return  "Test : \n guid = "+nb+"\n search = "+texte;
    }
    @GetMapping(value = "/rss25SB/resume/xml",produces = MediaType.APPLICATION_XML_VALUE)
    public String getRSSinXML(){
    return "<result><response>Message reçu:</response>";
    }
//    @GetMapping(value="/rss25SB/resume/xml")
//    public String get

    // QUESTION 1.4.1
    // @GetMapping(value = "/rss25SB/resume/xml/{id}",produces = MediaType.APPLICATION_XML_VALUE)
    // public ResponseEntity<String> getRSSinXMLById(@PathVariable long id){

    //     Optional<Item> itemOpt = itemRepository.findById(id); // on est cense recuperer un objet de type Item

    //     if (itemOpt.isPresent()) {
    //         Item item = itemOpt.get();
    //         String xml = Utils.convertItemToXml(item); //convertir le Item au format XMl
    //         return ResponseEntity.ok(xml);
    //     } else {
    //         // Générer une réponse XML d'erreur
    //         String errorXml = "<error><id>" + id + "</id><status>ERROR</status></error>";
    //         return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorXml);
    //     }
    // }


//    // QUESTION 1.4.2
//    @GetMapping(value = "/rss25SB/html/{id}", produces = MediaType.TEXT_HTML_VALUE)
//    public ResponseEntity<String> getItemAsHtmlById(@PathVariable long id) {
//    Optional<Item> optionalItem = itemRepository.findById(id);
//
//    if (optionalItem.isEmpty()) {
//
//        String errorHtml = "<html><body><h1>Erreur</h1><p>Identifiant : " + id + "</p><p>Status : ERROR</p></body></html>";
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorHtml);
//    }
//
//    try {
//        String itemXml = Utils.convertItemToXml(optionalItem.get()); //recuperer le xml
//
//
//        return ResponseEntity.ok(Utils.convertItemToHtml(itemXml));
//
//    } catch (Exception e) {
//        e.printStackTrace();
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                .body("<html><body><h1>Erreur serveur</h1><p>" + e.getMessage() + "</p></body></html>");
//    }

    //1.5.1
//}

@GetMapping(value = "/rss25SB/resume/xml/{id}",produces = MediaType.APPLICATION_XML_VALUE)
     public ResponseEntity<Object> getRSSinXMLById(@PathVariable long id){
    Optional<Item> itemOpt = itemRepository.findById(id); // on est cense recuperer un objet de type Item

    if (itemOpt.isPresent()) {
        Item item = itemOpt.get();
        ItemJAXB itemJAXB = ItemConversionService.toJAXB(item); //convertir le Item au format XMl
        return ResponseEntity.ok(itemJAXB);
     }else {
        // Générer une réponse XML d'erreur
        String errorXml = "<error><id>" + id + "</id><status>ERROR</status></error>";
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorXml);


}
     }
    }
