package fr.univrouen.rss25SB.entity;

import fr.univrouen.rss25SB.model.Role;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Author {
    @Id @GeneratedValue
    private Long id;

    private String name;
    private String uri;
    private Role role;
}

