package fr.univrouen.rss25SB.model;
import jakarta.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
public class content {
    @XmlAttribute
    private String type;
    @XmlAttribute
    private String src;

    public content(String type, String src) {
        super();
        this.type = type;
        this.src = src;
    }
    public content() {

    }

}
