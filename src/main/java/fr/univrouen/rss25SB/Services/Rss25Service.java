package fr.univrouen.rss25SB.Services;

import fr.univrouen.rss25SB.entity.*;
import fr.univrouen.rss25SB.model.FeedJAXB;
import fr.univrouen.rss25SB.repository.*;
import fr.univrouen.rss25SB.utils.Utils;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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
}
