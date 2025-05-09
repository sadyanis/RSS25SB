package fr.univrouen.rss25SB.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Category {
    @Id @GeneratedValue
    private Long id;

    private String term;
}

