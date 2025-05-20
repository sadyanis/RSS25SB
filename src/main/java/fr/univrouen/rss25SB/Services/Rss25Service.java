package fr.univrouen.rss25SB.Services;

import fr.univrouen.rss25SB.entity.Feed;
import fr.univrouen.rss25SB.entity.Item;
import fr.univrouen.rss25SB.model.FeedJAXB;
import fr.univrouen.rss25SB.repository.FeedRepository;
import fr.univrouen.rss25SB.repository.ItemRepository;
import fr.univrouen.rss25SB.utils.Utils;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class Rss25Service {
    private final FeedRepository feedRep;
    private final ItemRepository itemRep;
    public Rss25Service(FeedRepository feedRep, ItemRepository itemRep) {
        this.feedRep = feedRep;
        this.itemRep = itemRep;
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
