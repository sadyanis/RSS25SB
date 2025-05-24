package fr.univrouen.rss25SB.controllers;

import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Optional;

import fr.univrouen.rss25SB.Services.Rss25Service;
import fr.univrouen.rss25SB.model.ItemsSummaryJAXB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import fr.univrouen.rss25SB.entity.Feed;
import fr.univrouen.rss25SB.entity.Item;
import fr.univrouen.rss25SB.repository.FeedRepository;
import fr.univrouen.rss25SB.repository.ItemRepository;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

@RestController
@RequestMapping("/rss25SB")
public class Rss25SBController {


    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    Rss25Service rss25Service;

    @Autowired
    private FeedRepository feedRepository;

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Map<String, String>> deleteArticle(@PathVariable Long id) {
        return rss25Service.deleteArticle(id);
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
    @GetMapping(value = "/rss25SB/resume/xml/{id}",produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<Object> getRSSinXMLById(@PathVariable long id){
        return rss25Service.getRSSinXMLById(id);
    }
    @GetMapping(value = "/rss25SB/resume/xml",produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<ItemsSummaryJAXB> getRSSinXML(){
        return rss25Service.getRSSinXML();
    }
}
