package fr.univrouen.rss25SB.controllers;

import fr.univrouen.rss25SB.model.*;
import org.hibernate.cache.spi.support.CacheUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
public class PostController {
 @PostMapping(value="/testpost",consumes = "application/xml")
    public String testPost(@RequestBody String flux){
     return ("<result><response>Message reçu: </response>  "+ flux +"</result>" );
 }
 @PostMapping(value="/postrss",produces = MediaType.APPLICATION_XML_VALUE)
 @ResponseBody
    public String postRSS(){
     TestRSS testRSS = new TestRSS();
     return testRSS.loadFileXML();
 }

    @PostMapping(value = "/xml", produces = MediaType.APPLICATION_XML_VALUE)
    @ResponseBody
    public item  getXML() {
     category cat = new category("12345678");
     author aut = new author("nom", "prenom", "email");
        Image img = new Image("image", "image.png", "image/jpeg", 255);
        content cont = new content("content", "content");
        item it = new item ("12345678", "123", "2023-10-01",cat,img,cont,aut);
        return it;
    }
}
