package fr.univrouen.rss25SB.controllers;

import fr.univrouen.rss25SB.model.TestRSS;
import fr.univrouen.rss25SB.model.item;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

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
 @PostMapping(value="/xml", produces = MediaType.APPLICATION_XML_VALUE)
 @ResponseBody
 public item getXML(){
     item it = new item("12345678","Test item","2022-05-01T11:22:33");
     return it;
 }
}
