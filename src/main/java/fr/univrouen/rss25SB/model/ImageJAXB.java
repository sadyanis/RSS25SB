package fr.univrouen.rss25SB.model;

import jakarta.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
public class ImageJAXB {
    @XmlAttribute
    private String type;
    @XmlAttribute
    private String href;
    @XmlAttribute
    private String alt;
    @XmlAttribute
    private int length;

    public ImageJAXB(String type, String href, String alt, int length) {
        super();
        this.type = type;
        this.href = href;
        this.alt = alt;
        this.length = length;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getHref() {
        return href;
    }
    public void setHref(String href) {
        this.href = href;
    }
    public String getAlt() {
        return alt;
    }
    public void setAlt(String alt) {
        this.alt = alt;
    }
    public int getLength() {
        return length;
    }
    public void setLength(int length) {
        this.length = length;
    }
    public ImageJAXB() {
        // Default constructor
    }

}
