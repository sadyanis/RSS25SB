package fr.univrouen.rss25SB.model;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlElement;
import java.util.List;

@XmlRootElement(name="items")
@XmlAccessorType(XmlAccessType.FIELD)
public class ItemsSummaryJAXB {
    @XmlElement(namespace="http://univrouen.fr/rss25")
    private List<ItemSummary> item;

    public ItemsSummaryJAXB(List<ItemSummary> item) {
        this.item = item;
    }
    public List<ItemSummary> getItem() {
        return item;
    }
    public void setItem(List<ItemSummary> item) {
        this.item = item;
    }
    public ItemsSummaryJAXB() {
    }

}
