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







    // III.2

}
