package fr.univrouen.rss25SB.controllers;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.univrouen.rss25SB.entity.Feed;
import fr.univrouen.rss25SB.entity.Item;
import fr.univrouen.rss25SB.repository.FeedRepository;
import fr.univrouen.rss25SB.repository.ItemRepository;

@RestController
@RequestMapping("/rss25SB")
public class Rss25SBController {


    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private FeedRepository feedRepository;

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Map<String, String>> deleteArticle(@PathVariable Long id) {
        Optional<Item> itemOpt = itemRepository.findById(id);
        
        if (itemOpt.isEmpty()) {
            return ResponseEntity.ok(Map.of("status", "ERROR"));
        }

        Item item = itemOpt.get();
        Feed feed = item.getFeed();

        itemRepository.deleteById(id);

        // Vérifier si le feed est vide après suppression
        if (feed != null && feed.getItems().isEmpty()) {
            feedRepository.deleteById(feed.getId());
        }

        return ResponseEntity.ok(Map.of(
            "id", String.valueOf(id),
            "status", "DELETED"
        ));
    }
}
