package fr.univrouen.rss25SB.entity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Item {
    @Id @GeneratedValue
    private Long id;

    private String guid;
    private String title;
    private String content;
    private LocalDateTime published;
    private LocalDateTime updated;

    @ManyToOne
    private Feed feed;

    @ManyToOne(cascade = CascadeType.ALL)
    private Author author;

    @ManyToOne(cascade = CascadeType.ALL)
    private Image image;

    @ManyToOne(cascade = CascadeType.ALL)
    private Category category;
}

