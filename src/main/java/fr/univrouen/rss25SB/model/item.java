package fr.univrouen.rss25SB.model;


import jakarta.xml.bind.annotation.*;

import java.util.Date;

@XmlRootElement(name="item")
@XmlAccessorType(XmlAccessType.FIELD)
public class item {
    @XmlElement
    private String guid;
    @XmlElement
    private String title;
    @XmlElement
    private category category;
    @XmlElement
    private String pubDate;
    @XmlElement
    private Image image;
    @XmlElement
    private content content;
    @XmlElement
    private author author;



    public item(String guid, String title, String published , category category , Image image, content content, author author) {
        super();
        this.guid = guid;
        this.title = title;
        this.pubDate = published;
        this.category = category;
        this.image = image;
        this.content = content;
        this.author = author;
    }
    public item(){

    }


    @Override
    public String toString() {
        return ("Article : "+ title + "\n(" + guid + ") Le =  "+pubDate + category +  "\n");
    }
}
