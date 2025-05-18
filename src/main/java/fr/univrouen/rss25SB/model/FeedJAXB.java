package fr.univrouen.rss25SB.model;

import jakarta.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "feed", namespace = "http://univrouen.fr/rss25")
@XmlAccessorType(XmlAccessType.FIELD)
public class FeedJAXB {

    @XmlAttribute(name = "lang")
    private String lang;

    @XmlAttribute(name = "ver")
    private String ver;

    @XmlElement(name = "title", namespace = "http://univrouen.fr/rss25")
    private String title;

    @XmlElement(name = "pubDate", namespace = "http://univrouen.fr/rss25")
    private String pubDate;

    @XmlElement(name = "copyright", namespace = "http://univrouen.fr/rss25")
    private String copyright;

    @XmlElement(name = "link", namespace = "http://univrouen.fr/rss25")
    private Link link;

    @XmlElement(name = "item", namespace = "http://univrouen.fr/rss25")
    private List<ItemJAXB> items;

    

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

    public String getPubDate() {
        return pubDate;
    }
    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public String getCopyright() {
        return copyright;
    }
    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public Link getLink() {
        return link;
    }
    public void setLink(Link link) {
        this.link = link;
    }

    public List<ItemJAXB> getItems() {
        return items;
    }
    public void setItems(List<ItemJAXB> items) {
        this.items = items;
    }

    
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Link {
        @XmlAttribute(name = "rel")
        private String rel;

        @XmlAttribute(name = "type")
        private String type;

        @XmlAttribute(name = "href")
        private String href;

        
        public String getRel() { return rel; }
        public void setRel(String rel) { this.rel = rel; }

        public String getType() { return type; }
        public void setType(String type) { this.type = type; }

        public String getHref() { return href; }
        public void setHref(String href) { this.href = href; }
    }
}