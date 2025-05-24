

package fr.univrouen.rss25SB.model;

import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlElement;



@XmlAccessorType(XmlAccessType.FIELD)
public class ItemSummary {
    @XmlElement(namespace="http://univrouen.fr/rss25")
    private int id;
    @XmlElement(namespace="http://univrouen.fr/rss25")
    private String guid;
    @XmlElement(namespace="http://univrouen.fr/rss25")
    private String date;

    public ItemSummary() {

    }

    public ItemSummary(String guid, int id, String date) {
        this.guid = guid;
        this.id = id;
        this.date = date;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Article : " + id + " (" + guid + ") Le = " + date;
    }
}


