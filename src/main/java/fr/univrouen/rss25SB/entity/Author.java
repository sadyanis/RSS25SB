package fr.univrouen.rss25SB.entity;

@Entity
public class Author {
    @Id @GeneratedValue
    private Long id;

    private String name;
    private String uri;
    private Role role;
}

