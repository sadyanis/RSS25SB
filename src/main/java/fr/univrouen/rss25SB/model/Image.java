package fr.univrouen.rss25SB.model;

import jakarta.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
public class Image {
    @XmlAttribute
    private String type;
    @XmlAttribute
    private String href;
    @XmlAttribute
    private String alt;
    @XmlAttribute
    private int length;

    public Image(String type, String href, String alt, int length) {
        super();
        this.type = type;
        this.href = href;
        this.alt = alt;
        this.length = length;
    }
    public Image() {
        // Default constructor
    }

}
