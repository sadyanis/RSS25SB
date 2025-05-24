package fr.univrouen.rss25SB.entity;


import jakarta.persistence.*;

@Entity
public class Content {
    @Id @GeneratedValue
    private Long id;
    @Lob
    @Basic(fetch = FetchType.EAGER)
    @Column(name= "content")
    private String content;
    private String type;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
}

