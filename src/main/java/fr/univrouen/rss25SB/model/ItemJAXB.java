package fr.univrouen.rss25SB.model;


import jakarta.xml.bind.annotation.*;

import java.util.Date;
import java.util.List;

@XmlRootElement(name="item")
@XmlAccessorType(XmlAccessType.FIELD)
public class ItemJAXB {
    @XmlElement(namespace="http://univrouen.fr/rss25")
    private String guid;

    @XmlElement(namespace="http://univrouen.fr/rss25")
    private String title;

    @XmlElement(namespace="http://univrouen.fr/rss25")
    private List<CategoryJAXB> category;

    @XmlElement(name="published", namespace="http://univrouen.fr/rss25") 
    private String published;

    @XmlElement(name="updated", namespace="http://univrouen.fr/rss25")
    private String updated;
    @XmlElement(name="image",namespace="http://univrouen.fr/rss25")
    private ImageJAXB image;

    public String getPublished() {
        return published;
    }
    public void setPublished(String published) {
        this.published = published;
    }
    public List<AuthorJAXB> getContributor() {
        return contributors;
    }
    public void setContributor(List<AuthorJAXB> contributor) {
        this.contributors = contributor;
    }


    @XmlElement(name="content", namespace="http://univrouen.fr/rss25")
    private ContentJAXB content;

    @XmlElement(name="author", namespace="http://univrouen.fr/rss25")
    private List<AuthorJAXB> authors;

    @XmlElement(name="contributor", namespace="http://univrouen.fr/rss25")
    private List<AuthorJAXB> contributors;



    public ItemJAXB(String guid, String title, String published , List<CategoryJAXB> category , ImageJAXB image, ContentJAXB content, List<AuthorJAXB> author) {
        super();
        this.guid = guid;
        this.title = title;
        this.published = published;
        this.category = category;
        this.image = image;
        this.content = content;
        this.authors = author;
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
    public List<CategoryJAXB> getCategory() {
        return category;
    }
    public void setCategory(List<CategoryJAXB> category) {
        this.category = category;
    }
    public String getPubDate() {
        return published;
    }
    public void setPubDate(String pubDate) {
        this.published = pubDate;
    }
    public String getUpdated() {
        return updated;
    }
    public void setUpdated(String updated) {
        this.updated = updated;
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
    public List<AuthorJAXB> getAuthor() {
        return authors;
    }
    public void setAuthor(List<AuthorJAXB> author) {
        this.authors = author;
    }
    public ItemJAXB(){

    }


    @Override
    public String toString() {
        return ("Article : "+ title + "\n(" + guid + ") Le =  "+published + category +  "\n");
    }
}
