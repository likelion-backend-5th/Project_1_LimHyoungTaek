package com.be05.market.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "sales_item")
public class ItemEntity {
    public ItemEntity(Long id, String title, String description, String imageURL,
                      Long minPriceWanted, String status, String writer, String password) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.imageURL = imageURL;
        this.minPriceWanted = minPriceWanted;
        this.status = status;
        this.writer = writer;
        this.password = password;
    }

    public ItemEntity() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public Long getMinPriceWanted() {
        return minPriceWanted;
    }

    public void setMinPriceWanted(Long minPriceWanted) {
        this.minPriceWanted = minPriceWanted;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private String imageURL;
    private Long minPriceWanted;
    private String status;
    private String writer;
    private String password;
}
