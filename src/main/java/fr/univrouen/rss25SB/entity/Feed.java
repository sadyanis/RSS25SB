package fr.univrouen.rss25SB.entity;

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
}
