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

    public void setId(Long id) {
        this.id = id;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getVer() {
        return ver;
    }

    public void setVer(String ver) {
        this.ver = ver;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getPubDate() {
        return pubDate;
    }

    public void setPubDate(LocalDateTime pubDate) {
        this.pubDate = pubDate;
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public String getSelfLink() {
        return selfLink;
    }

    public void setSelfLink(String selfLink) {
        this.selfLink = selfLink;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public Long getId() {
        return this.id;
    }

    public List<Item> getItems() {
        return this.items;
    }
}
