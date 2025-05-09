package fr.univrouen.rss25SB.controllers;

import fr.univrouen.rss25SB.entity.Item;
import fr.univrouen.rss25SB.model.*;
import fr.univrouen.rss25SB.repository.ItemRepository;
import fr.univrouen.rss25SB.utils.Utils;

import org.hibernate.cache.spi.support.CacheUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Optional;

@RestController
public class PostController {


    @Autowired
    ItemRepository itemRepository;

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


    @PostMapping(value = "/rss25SB/insert", consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<String> insertItem(@RequestBody String itemXml) {
        try {
            if (!Utils.isValidXml(itemXml)) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("<rss25SB><status>ERROR</status><message>XML invalid according to XSD</message></rss25SB>");
            }

            Item newItem = Utils.convertXmlToItem(itemXml);

            Optional<Item> existingItem = itemRepository.findByTitleAndDate(newItem.getTitle(), newItem.getPublished());
            if (existingItem.isPresent()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("<rss25SB><status>ERROR</status><message>Article already exists</message></rss25SB>");
            }

            
            
            itemRepository.save(newItem);

            
            String responseXml = "<rss25SB><status>INSERTED</status><id>" + newItem.getId() + "</id></rss25SB>";
            return ResponseEntity.ok(responseXml);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("<rss25SB><status>ERROR</status><message>" + e.getMessage() + "</message></rss25SB>");
        }
    }
}
