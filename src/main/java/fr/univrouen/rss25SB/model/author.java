package fr.univrouen.rss25SB.model;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class author {
    @XmlElement
    private String name;
    @XmlElement
    private String email;
    @XmlElement
    private String uri;

    public author(String name, String email, String uri) {
        super();
        this.name = name;
        this.email = email;
        this.uri = uri;
    }
    public author() {
        // Default constructor
    }


}
