package me.nyxz.scrapers.realestate.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "property", schema = "realestate")
public class Property {

    public static final String CURRENCY = "EUR";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "type")
    private String type;

    @Column(name = "neighbourhood")
    private String neighbourhood;

    @Column(name = "price")
    private Long price;

    @Column(name = "price_txt")
    private String priceText;

    @Column(name = "description", length = 10_000)
    @Lob
    private String description;

    @Column(name = "size")
    private String size;

    @Column(name = "url", length = 2_083)
    private String url;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public Property setType(String type) {
        this.type = type;
        return this;
    }

    public String getNeighbourhood() {
        return neighbourhood;
    }

    public Property setNeighbourhood(String neighbourhood) {
        this.neighbourhood = neighbourhood;
        return this;
    }

    public Long getPrice() {
        return price;
    }

    public Property setPrice(Long price) {
        this.price = price;
        return this;
    }

    public String getPriceText() {
        return priceText;
    }

    public Property setPriceText(String priceText) {
        this.priceText = priceText;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Property setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getSize() {
        return size;
    }

    public Property setSize(String size) {
        this.size = size;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public Property setUrl(String url) {
        this.url = url;
        return this;
    }

    @Override
    public String toString() {
        return String.format("%-30s | %-16s | %-20s | %-8d%s | %-24s | %s | %s",
                getType(),
                getSize(),
                getNeighbourhood(),
                getPrice(),
                CURRENCY,
                getPriceText(),
                getUrl(),
                getDescription());
    }
}
