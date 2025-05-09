package fr.univrouen.rss25SB.utils;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import javax.xml.transform.*;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import fr.univrouen.rss25SB.entity.Item;

public class Utils {
    static public String convertItemToXml(Item item) {
        return "<item>" +
               "<guid>" + item.getGuid() + "</guid>" +
               "<title>" + item.getTitle() + "</title>" +
               "<content>" + item.getContent() + "</content>" +
               "<published>" + item.getPublished() + "</published>" +
               "<updated>" + item.getUpdated() + "</updated>" +
               "<author>" +
               "<name>" + item.getAuthor().getName() + "</name>" +
               "</author>" +
               "<image>" +
               "<alt>" + item.getImage().getAlt() + "</alt>" +
               "<href>" + item.getImage().getHref() + "</href>" +
               "</image>" +
               "<category>" +
               "<term>" + item.getCategory().getTerm() + "</term>" +
               "</category>" +
               "</item>";
    }

    static public String convertItemToHtml(String itemXml) throws Exception{

        try {
            InputStreamResource resource = new InputStreamResource(
                new ClassPathResource("xslt/item.xslt").getInputStream()
            );
            Source xslt = new StreamSource(resource.getInputStream());
            
            TransformerFactory factory = TransformerFactory.newInstance();
            Transformer transformer = factory.newTransformer(xslt);

            Source xmlSource = new StreamSource(new StringReader(itemXml));
            StringWriter htmlWriter = new StringWriter();
            Result outputTarget = new StreamResult(htmlWriter);
            transformer.transform(xmlSource, outputTarget); //proceder la traaformation

        
            return htmlWriter.toString();

        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

    }
}
