//package fr.univrouen.rss25SB.model;
//
//import jakarta.xml.bind.annotation.XmlAccessType;
//import jakarta.xml.bind.annotation.XmlAccessorType;
//import jakarta.xml.bind.annotation.XmlElement;
//import jakarta.xml.bind.annotation.XmlRootElement;
//
//import java.util.Date;
//
//@XmlRootElement(name="item")
//@XmlAccessorType(XmlAccessType.PROPERTY)
//public class ItemSummary {
//    @XmlElement
//    private int id;
//    @XmlElement
//    private Date pubDate;
//    @XmlElement
//    private String guid;
//
//    public ItemSummary(int id, String guid, Date pubDate) {
//        super();
//        this.id = id;
//        this.guid = guid;
//        this.pubDate = pubDate;
//
//    }
//
//
//
//
//    @Override
//    public String toString() {
//        return ("Article : "+ guid + "\n(" + id + ") Le =  "+pubDate + "\n");
//    }
//
//}

package fr.univrouen.rss25SB.model;


import jakarta.xml.bind.annotation.*;

import java.util.Date;

@XmlRootElement(name="item")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class ItemSummary  {
    @XmlElement
    private int id;
    @XmlElement
    private String guid;
    @XmlElement
    private String pubDate;

    public ItemSummary (String guid, int id, String published) {
        super();
        this.guid = guid;
        this.id = id;
        this.pubDate = published;
    }
    public ItemSummary (){

    }


    @Override
    public String toString() {
        return ("Article : "+ id + "\n(" + guid + ") Le =  "+pubDate + "\n");
    }
}

