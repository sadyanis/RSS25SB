package fr.univrouen.rss25SB.model;


import jakarta.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.PROPERTY)
public class CategoryJAXB {

    private String term;

    

    public CategoryJAXB(String term) {
        super();
        this.term = term;
    }
    @XmlAttribute
    public String getTerm() {
        return term;
    }
    public void setTerm(String term) {
        this.term = term;
    }
    public CategoryJAXB() {

    }



}
