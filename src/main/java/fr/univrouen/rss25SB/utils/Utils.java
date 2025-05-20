package fr.univrouen.rss25SB.utils;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import javax.xml.XMLConstants;
import javax.xml.transform.*;

import fr.univrouen.rss25SB.model.*;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.xml.sax.SAXException;

import fr.univrouen.rss25SB.entity.Author;
import fr.univrouen.rss25SB.entity.Content;
import fr.univrouen.rss25SB.entity.Feed;
import fr.univrouen.rss25SB.entity.Item;
import fr.univrouen.rss25SB.entity.Image;
import fr.univrouen.rss25SB.entity.Category;
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
               "<name>" + item.getAuthor().get(0).getName() + "</name>" +
               "</author>" +
               "<image>" +
               "<alt>" + item.getImage().getAlt() + "</alt>" +
               "<href>" + item.getImage().getHref() + "</href>" +
               "</image>" +
               "<category>" +
               "<term>" + item.getCategory().get(0).getTerm() + "</term>" +
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

    // public static Item convertXmlToItem(String xml) throws JAXBException {
    //     JAXBContext context = JAXBContext.newInstance(ItemJAXB.class);
    //     Unmarshaller unmarshaller = context.createUnmarshaller();
    //     StringReader reader = new StringReader(xml);
    //     return (ItemJAXB) unmarshaller.unmarshal(reader);
    // }

    public static FeedJAXB convertXmlToFeed(String feedXml) throws JAXBException{
        JAXBContext context = JAXBContext.newInstance(FeedJAXB.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        StringReader reader = new StringReader(feedXml);
        return (FeedJAXB) unmarshaller.unmarshal(reader);
    }

    public static Feed toFeedEntity(FeedJAXB feedJaxb) {
        Feed feedEntity = new Feed();

        feedEntity.setLang(feedJaxb.getLang());
        feedEntity.setVer(feedJaxb.getVer());
        feedEntity.setTitle(feedJaxb.getTitle());
        feedEntity.setCopyright(feedJaxb.getCopyright());

        if (feedJaxb.getPubDate() != null) {
            feedEntity.setPubDate(LocalDateTime.parse(feedJaxb.getPubDate()));
        }

        if (feedJaxb.getLink() != null) {
            feedEntity.setSelfLink(feedJaxb.getLink().getHref());
        }

        if (feedJaxb.getItems() != null) {
            List<Item> itemsEntities = feedJaxb.getItems().stream()
                .map(itemJaxb -> {
                    Item itemEntity = toItemEntity(itemJaxb);
                    itemEntity.setFeed(feedEntity); 
                    return itemEntity;
                })
                .collect(Collectors.toList());

            feedEntity.setItems(itemsEntities);
        } else {
            feedEntity.setItems(new ArrayList<>());
        }

        return feedEntity;
    }

    public static Item toItemEntity(ItemJAXB itemJaxb) {
        Item itemEntity = new Item();

        itemEntity.setGuid(itemJaxb.getGuid());
        itemEntity.setTitle(itemJaxb.getTitle());

        if (itemJaxb.getPubDate() != null) {
            try {
                LocalDateTime published = LocalDateTime.parse(itemJaxb.getPubDate());
                itemEntity.setPublished(published);
            } catch (DateTimeParseException e) {
                System.err.println("Erreur conversion date pubDate : " + e.getMessage());
            }
        }else{
            try{
                LocalDateTime updated = LocalDateTime.parse(itemJaxb.getUpdated());
                itemEntity.setUpdated(updated);

            }catch(DateTimeParseException e){
                System.err.println("Erreur conversion date updated : " + e.getMessage());
            }
        }

        
        if (itemJaxb.getContent() != null) {
            Content contentEntity = toContentEntity(itemJaxb.getContent());
            itemEntity.setContent(contentEntity);
        }

        
       if (itemJaxb.getAuthor() != null) {
        // getAuthor() retourne une liste d'AuthorJAXB
        List<Author> authors = new ArrayList<>();
        for (AuthorJAXB authorJaxb : itemJaxb.getAuthor()) {
            authors.add(toAuthorEntity(authorJaxb));
        }
        itemEntity.setAuthor(authors);
       }

       if (itemJaxb.getContributor() != null) {
        // getContributor() retourne une liste d'ContributorJAXB
        List<Author> contributors = new ArrayList<>();
        for (AuthorJAXB contributorJaxb : itemJaxb.getContributor()) {
            Author author = toAuthorEntity(contributorJaxb);
            author.setRole(Role.CONTRIBUTOR);
            contributors.add(author);           
        }
        itemEntity.setAuthor(contributors);
       }

       if (itemJaxb.getCategory() != null) {
        // getCategory() retourne une liste de CategoryJAXB
        List<Category> categories = new ArrayList<>();
        for (CategoryJAXB categoryJaxb : itemJaxb.getCategory()) {
            categories.add(toCategoryEntity(categoryJaxb));
        }
        itemEntity.setCategory(categories);
       }
       // Copier Image
        if (itemJaxb.getImage() != null) {
            Image imageEntity = toImageEntity(itemJaxb.getImage());
            itemEntity.setImage(imageEntity);
        }

        /*
        
        if (itemJaxb.getAuthor() != null) {
            Author authorEntity = toAuthorEntity(itemJaxb.getAuthor());
            authorEntity.setRole(Role.AUTHOR);  // ou null par défaut
            itemEntity.setAuthor(authorEntity);
        }// } else if (itemJaxb.getContributor() != null) {
        //     Author contributorEntity = toAuthorEntity(itemJaxb.getContributor());
        //     contributorEntity.setRole(Role.CONTRIBUTOR);  // ici on précise le rôle
        //     itemEntity.setAuthor(contributorEntity);
        // } */

        

        // Copier Category
//        if (itemJaxb.getCategory() != null) {
//            Category categoryEntity = toCategoryEntity(itemJaxb.getCategory());
//            itemEntity.setCategory(categoryEntity);
//        }

        return itemEntity;
    }

    public static Content toContentEntity(ContentJAXB cJaxb) {
        if (cJaxb == null) return null;
        Content cEntity = new Content();
        cEntity.setType(cJaxb.getType());
        
        cEntity.setContent(cJaxb.getContent()); // ajouter src apres
        return cEntity;
    }

    public static Author toAuthorEntity(AuthorJAXB aJaxb) {
        if (aJaxb == null) return null;
        Author aEntity = new Author();
        aEntity.setName(aJaxb.getName());
        aEntity.setUri(aJaxb.getUri());
        return aEntity;
    }

    public static Category toCategoryEntity(CategoryJAXB catJaxb) {
        if (catJaxb == null) return null;
        Category catEntity = new Category();
        catEntity.setTerm(catJaxb.getTerm());
        return catEntity;
    }

    public static Image toImageEntity(ImageJAXB iJaxb) {
        if (iJaxb == null) return null;
        Image iEntity = new Image();
        iEntity.setAlt(iJaxb.getAlt());
        iEntity.setType(iJaxb.getType());
        iEntity.setHref(iJaxb.getHref());
        return iEntity;
    }


}
