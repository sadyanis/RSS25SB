package fr.univrouen.rss25SB.utils;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import javax.xml.XMLConstants;
import javax.xml.transform.*;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.xml.sax.SAXException;

import fr.univrouen.rss25SB.entity.Item;
import fr.univrouen.rss25SB.model.item;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;

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

    static public boolean isValidXml(String itemXml) {
        try {
            // Charger le schéma XSD
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Source schemaSource = new StreamSource(Utils.class.getClassLoader().getResourceAsStream("xsd/feed.xsd"));
            Schema schema = factory.newSchema(schemaSource);

            // Créer un validateur
            Validator validator = schema.newValidator();
            Source xmlSource = new StreamSource(new StringReader(itemXml));

            // Valider le flux XML
            validator.validate(xmlSource);
            return true;
        } catch (SAXException | IOException e) {
            return false;
        }
    }

    public static Item convertXmlToItem(String xml) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(item.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        StringReader reader = new StringReader(xml);
        return (Item) unmarshaller.unmarshal(reader);
    }
}
