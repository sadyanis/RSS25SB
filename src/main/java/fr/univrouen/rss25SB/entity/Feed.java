package fr.univrouen.rss25SB.entity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Feed {
    @Id @GeneratedValue
    private Long id;

    private String lang;
    private String ver;
    private String title;
    private LocalDateTime pubDate;
    private String copyright;
    private String selfLink;

    @OneToMany(mappedBy = "feed", cascade = CascadeType.ALL)
    private List<Item> items;

    public Long getId() {
        return this.id;
    }

    public List<Item> getItems() {
        return this.items;
    }
}
