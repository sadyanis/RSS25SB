package fr.univrouen.rss25SB.model;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class ContributorJAXB {
    @XmlElement(namespace="http://univrouen.fr/rss25")
    private String name;
    @XmlElement(namespace="http://univrouen.fr/rss25")
    private String email;
    @XmlElement(namespace="http://univrouen.fr/rss25")
    private String uri;

    public ContributorJAXB(String name, String email, String uri) {
        super();
        this.name = name;
        this.email = email;
        this.uri = uri;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getUri() {
        return uri;
    }
    public void setUri(String uri) {
        this.uri = uri;
    }
    public ContributorJAXB() {
        // Default constructor
    }


}

