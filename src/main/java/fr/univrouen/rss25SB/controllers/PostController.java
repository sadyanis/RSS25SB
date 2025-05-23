package fr.univrouen.rss25SB.controllers;

import fr.univrouen.rss25SB.Services.Rss25Service;
import fr.univrouen.rss25SB.entity.Feed;
import fr.univrouen.rss25SB.entity.Item;
import fr.univrouen.rss25SB.model.*;
import fr.univrouen.rss25SB.repository.FeedRepository;
import fr.univrouen.rss25SB.repository.ItemRepository;
import fr.univrouen.rss25SB.utils.Utils;

import org.hibernate.cache.spi.support.CacheUtils;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.context.properties.PropertyMapper.Source;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;



import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.xml.transform.*;
import java.io.*;



@RestController
public class PostController {

    @Autowired
    Rss25Service rss25Service;
    @Autowired
    ItemRepository itemRepository;

    @Autowired
    FeedRepository feedRepository;

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
    public ItemJAXB  getXML() {
        List<CategoryJAXB> contributors = new ArrayList<>();
        List<AuthorJAXB> authors = new ArrayList<>();
        CategoryJAXB cat = new CategoryJAXB("12345678");
        AuthorJAXB aut = new AuthorJAXB("nom", "prenom", "email");
        ImageJAXB img = new ImageJAXB("image", "image.png", "image/jpeg", 255);
        ContentJAXB cont = new ContentJAXB("content", "content");
        contributors.add(cat);
        authors.add(aut);
        ItemJAXB it = new ItemJAXB ("12345678", "123", "2023-10-01",contributors,img,cont,authors);
        return it;
    }


    @PostMapping(value = "/rss25SB/insert", consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_XML_VALUE)

    public ResponseEntity<String> insertItem(@RequestBody String feedXml) {
        try{
            String reponse = rss25Service.insertFeedFromXml(feedXml);
            HttpStatus status = reponse.contains("<status>ERROR</status>")?HttpStatus.BAD_REQUEST:HttpStatus.OK;
            return ResponseEntity.status(status).body(reponse);
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("<rss25SB><status>ERROR</status><message>" + e.getMessage() + "</message></rss25SB>");
        }
    //     try {
    //         if (!Utils.isValidXml(feedXml)) {
    //             return ResponseEntity.status(HttpStatus.BAD_REQUEST)
    //                 .body("<rss25SB><status>ERROR</status><message>XML invalid according to XSD</message></rss25SB>");
    //         }
    
            
    //         //convertir le string xml en object jaxb d'abord
    //         FeedJAXB feedJaxb = Utils.convertXmlToFeed(feedXml) ;
            


    //         // convertir l'objet jaxb en feed entity
    //         Feed feed = Utils.toFeedEntity(feedJaxb);
    //         Long lastInsertedId = null;
            
            
    //         List<Item> filteredItems = new ArrayList<>();
    //         for (Item item : feed.getItems()) {
    //             List<Item> existingItem = itemRepository.findByTitleAndPublished(item.getTitle(), item.getPublished());
    //             if (existingItem.isEmpty()) {
    //                 filteredItems.add(item);
    //             }
    //         }
    //         feed.setItems(filteredItems);
    //         Feed savedFeed = feedRepository.save(feed);
    //         lastInsertedId = savedFeed.getId();
    //         // //boucle sur les items du feed convertit
    //         // for (Item item : feed.getItems()) {
    //         //     //Optional<Item> existingItem = itemRepository.findByTitleAndPublished(item.getTitle(), item.getPublished());
    //         //     List<Item> existingItem = itemRepository.findByTitleAndPublished(item.getTitle(), item.getPublished());
    //         //     System.out.println("ITEM: "+ existingItem);
    //         //     if (existingItem.isEmpty()) {
    //         //         // Associer l’item au feed persisté
    //         //         item.setFeed(savedFeed);
    //         //         Item saved = itemRepository.save(item);
    //         //         lastInsertedId = saved.getId();
    //         //         System.out.println("HOT3: "+lastInsertedId);
    //         //     }
    //         // }
    
    //         if (lastInsertedId == null) {
    //             return ResponseEntity.status(HttpStatus.BAD_REQUEST)
    //                 .body("<rss25SB><status>ERROR</status><message>No new articles to insert</message></rss25SB>");
    //         }
    
    //         String responseXml = "<rss25SB><status>INSERTED</status><id>" + lastInsertedId + "</id></rss25SB>";
    //         return ResponseEntity.ok(responseXml);
    
    //     } catch (Exception e) {
    //         e.printStackTrace();
    //         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
    //             .body("<rss25SB><status>ERROR</status><message>" + e.getMessage() + "</message></rss25SB>");
    //     }
    }

    @PostMapping(value = "/rss25SB/insertFile", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<String> insertFromFile(@RequestParam("file") MultipartFile file) {
        try {
            // lire le contenu du fichier XML en String
            String xml = new String(file.getBytes(), StandardCharsets.UTF_8);

            // appeler ta méthode d'insertion
            String response = rss25Service.insertFeedFromXml(xml);

            HttpStatus status = response.contains("<status>ERROR</status>") ? HttpStatus.BAD_REQUEST : HttpStatus.OK;
            return ResponseEntity.status(status).body(response);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("<rss25SB><status>ERROR</status><message>" + e.getMessage() + "</message></rss25SB>");
        }
    }


    // III.2
    @PostMapping("/convert")
    public ResponseEntity<byte[]> convertXml(@RequestParam("file") MultipartFile file) throws Exception {
        Source xslt = new StreamSource(new ClassPathResource("xslt/convert.xslt").getInputStream());

        Source xmlInput = new StreamSource(file.getInputStream());

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        Result output = new StreamResult(outputStream);

        TransformerFactory factory = TransformerFactory.newInstance();
        Transformer transformer = factory.newTransformer(xslt);
        transformer.transform(xmlInput, output);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"converted_rss25SB.xml\"")
                .contentType(MediaType.APPLICATION_XML)
                .body(outputStream.toByteArray());
    }
}
