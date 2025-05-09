package fr.univrouen.rss25SB.controllers;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.univrouen.rss25SB.entity.Item;

@RestController
public class GetController {
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

    // QUESTION 1.4
    @GetMapping(value = "/rss25SB/resume/xml/{id}",produces = MediaType.APPLICATION_XML_VALUE)
    public String getRSSinXMLById(@PathVariable int id){

        return "<result><response>Message reçu:</response>";
    }

}
