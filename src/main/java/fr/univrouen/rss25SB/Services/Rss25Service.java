package fr.univrouen.rss25SB.Services;

import fr.univrouen.rss25SB.entity.*;
import fr.univrouen.rss25SB.model.FeedJAXB;
import fr.univrouen.rss25SB.model.ItemJAXB;
import fr.univrouen.rss25SB.model.ItemSummary;
import fr.univrouen.rss25SB.model.ItemsSummaryJAXB;
import fr.univrouen.rss25SB.repository.*;
import fr.univrouen.rss25SB.utils.Utils;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class Rss25Service {
    private final FeedRepository feedRep;
    private final ItemRepository itemRep;
    private final CategoryRepository categoryRep;
    private final AuthorRepository authorRep;
    private final ImageRepository imageRep;


    public Rss25Service(FeedRepository feedRep, ItemRepository itemRep,
                       CategoryRepository categoryRep, AuthorRepository authorRep,
                       ImageRepository imageRep) {
        this.feedRep = feedRep;
        this.itemRep = itemRep;
        this.categoryRep = categoryRep;
        this.authorRep = authorRep;
        this.imageRep = imageRep;
    }
    @Transactional
    public String insertFeedFromXml(String feedXml) throws Exception {
        if(!Utils.isValidXml(feedXml)){
            return "<rss25SB><status>ERROR</status><message>XML invalid according to XSD</message></rss25SB>";
        }
        // convertir le fichier xml en objets
        FeedJAXB feedJAXB = Utils.convertXmlToFeed(feedXml);

        Feed feed = Utils.toFeedEntity(feedJAXB);

        List<Item> filtredItems = new ArrayList<>();
        for (Item item : feed.getItems()) {
            List<Item> existingItem = itemRep.findByTitleAndPublished(item.getTitle(), item.getPublished());
            if (existingItem.isEmpty()) {
                // Sauvegarder les catégories de l'item
                if (item.getCategory() != null) {
                    item.setCategory(item.getCategory().stream()
                        .map(category -> categoryRep.save(category))
                        .collect(Collectors.toList()));
                }
                
                // Sauvegarder les auteurs de l'item
              
                
                if (item.getAuthor() != null) {
                    item.setAuthor(item.getAuthor().stream()
                        .map(author -> authorRep.save(author))
                        .collect(Collectors.toList()));
                }
                
                // Sauvegarder l'image de l'item
                if (item.getImage() != null) {
                    item.setImage(imageRep.save(item.getImage()));
                }
                
                filtredItems.add(item);
            }
        }
        if(filtredItems.isEmpty()){
            return "<rss25SB><status>ERROR</status><message>No new articles to insert</message></rss25SB>";
        }
        feed.setItems(filtredItems);
        Feed feedSaved = feedRep.save(feed);
        return "<rss25SB><status>INSERTED</status><feedId>" + feedSaved.getId() + "</feedId></rss25SB>";
    }

    public ResponseEntity<ItemsSummaryJAXB> getRSSinXML() {
        List<Item> items = itemRep.findAll();
        //filtrer la liste des items pour ne garder que ceux qui ont un guid
        items = items.stream()
                .filter(item -> item.getGuid() != null)
                .toList(); 

        List<ItemSummary> summaries = items.stream()
                .map(item -> new ItemSummary(
                        item.getGuid(), item.getId(),
                        item.getPublished() != null ? item.getPublished().toString() : (item.getUpdated() != null ? item.getUpdated().toString() : "N/A")

                ))
                .toList();
        return ResponseEntity.ok(new ItemsSummaryJAXB(summaries));
    }

    public ResponseEntity<Object> getRSSinXMLById( long id) {
        Optional<Item> itemOpt = itemRep.findById(id);

        if (itemOpt.isPresent()) {
            Item item = itemOpt.get();
            ItemJAXB itemJAXB = ItemConversionService.toJAXB(item); //convertir le Item au format XMl
            return ResponseEntity.ok(itemJAXB);
        } else {
            // Générer une réponse XML d'erreur
            String errorXml = "<error><id>" + id + "</id><status>ERROR</status></error>";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorXml);

        }

    }
    @Transactional
    public ResponseEntity<Map<String, String>> deleteArticle( Long id) {
        Optional<Item> itemOpt = itemRep.findById(id);

        if (itemOpt.isEmpty()) {
            return ResponseEntity.ok(Map.of("status", "ERROR"));
        }

        Item item = itemOpt.get();
        Feed feed = item.getFeed();

        itemRep.deleteById(id);

        // Vérifier si le feed est vide après suppression
        if (feed != null && feed.getItems().isEmpty()) {
            feedRep.deleteById(feed.getId());
        }

        return ResponseEntity.ok(Map.of(
                "id", String.valueOf(id),
                "status", "DELETED"
        ));
    }

    public Optional<Item> getItemById(Long id) {
        return itemRep.findById(id);
    }
    public List<Item> getAllItems() {
        return itemRep.findAll();
    }

}
