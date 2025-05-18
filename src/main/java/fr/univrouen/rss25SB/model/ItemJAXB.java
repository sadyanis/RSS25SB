package fr.univrouen.rss25SB.model;


import jakarta.xml.bind.annotation.*;

import java.util.Date;

@XmlRootElement(name="item")
@XmlAccessorType(XmlAccessType.FIELD)
public class ItemJAXB {
    @XmlElement(namespace="http://univrouen.fr/rss25")
    private String guid;

    @XmlElement(namespace="http://univrouen.fr/rss25")
    private String title;

    @XmlElement(namespace="http://univrouen.fr/rss25")
    private CategoryJAXB category;

    @XmlElement(name="published", namespace="http://univrouen.fr/rss25") 
    private String published;

    @XmlElement(name="image",namespace="http://univrouen.fr/rss25")
    private ImageJAXB image;

    public String getPublished() {
        return published;
    }
    public void setPublished(String published) {
        this.published = published;
    }
    public AuthorJAXB getContributor() {
        return contributor;
    }
    public void setContributor(AuthorJAXB contributor) {
        this.contributor = contributor;
    }


    @XmlElement(name="content", namespace="http://univrouen.fr/rss25")
    private ContentJAXB content;

    @XmlElement(name="author", namespace="http://univrouen.fr/rss25")
    private AuthorJAXB author;

    @XmlElement(name="contributor", namespace="http://univrouen.fr/rss25")
    private AuthorJAXB contributor;



    public ItemJAXB(String guid, String title, String published , CategoryJAXB category , ImageJAXB image, ContentJAXB content, AuthorJAXB author) {
        super();
        this.guid = guid;
        this.title = title;
        this.published = published;
        this.category = category;
        this.image = image;
        this.content = content;
        this.author = author;
    }
    public String getGuid() {
        return guid;
    }
    public void setGuid(String guid) {
        this.guid = guid;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public CategoryJAXB getCategory() {
        return category;
    }
    public void setCategory(CategoryJAXB category) {
        this.category = category;
    }
    public String getPubDate() {
        return published;
    }
    public void setPubDate(String pubDate) {
        this.published = pubDate;
    }
    public ImageJAXB getImage() {
        return image;
    }
    public void setImage(ImageJAXB image) {
        this.image = image;
    }
    public ContentJAXB getContent() {
        return content;
    }
    public void setContent(ContentJAXB content) {
        this.content = content;
    }
    public AuthorJAXB getAuthor() {
        return author;
    }
    public void setAuthor(AuthorJAXB author) {
        this.author = author;
    }
    public ItemJAXB(){

    }


    @Override
    public String toString() {
        return ("Article : "+ title + "\n(" + guid + ") Le =  "+published + category +  "\n");
    }
}
