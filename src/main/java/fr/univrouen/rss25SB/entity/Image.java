package fr.univrouen.rss25SB.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Image {
    @Id @GeneratedValue
    private Long id;

    private String alt;
    private String type;
    private String href;
}

