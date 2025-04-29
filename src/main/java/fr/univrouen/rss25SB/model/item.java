package fr.univrouen.rss25SB.model;


import jakarta.xml.bind.annotation.*;

@XmlRootElement(name="item")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class item {
    @XmlAttribute
    private String guid;
    @XmlElement
    private String title;
    @XmlElement
    private String published;

    public item(String guid, String title, String published) {
        super();
        this.guid = guid;
        this.title = title;
        this.published = published;
    }
    public item(){

    }


    @Override
    public String toString() {
        return ("Article : "+ title + "\n(" + guid + ") Le =  "+published  );
    }
}
