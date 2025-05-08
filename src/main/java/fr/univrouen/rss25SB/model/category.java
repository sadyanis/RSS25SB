package fr.univrouen.rss25SB.model;


import jakarta.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.PROPERTY)
public class category {
@XmlAttribute
    private String term;

    public category(String term) {
        super();
        this.term = term;
    }
    public category() {

    }


}
