package fr.univrouen.rss25SB.model;
import jakarta.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
public class ContentJAXB {
    @XmlAttribute
    private String type;
    @XmlAttribute
    private String src;
    @XmlValue
    private String content;

    public ContentJAXB(String type, String src) {
        super();
        this.type = type;
        this.src = src;
    }
    public ContentJAXB() {

    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getSrc() {
        return src;
    }
    public void setSrc(String src) {
        this.src = src;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
