package me.nyxz.scrapers.realestate.dto;

public class SelectorConfig {

    private String type;
    private String neighbourhood;
    private String price;
    private String description;
    private String currency;
    private String size;
    private String url;

    private SelectorConfig() {
    }

    public static SelectorConfig init() {
        return new SelectorConfig();
    }

    public String getType() {
        return type;
    }

    public SelectorConfig setType(String type) {
        this.type = type;
        return this;
    }

    public String getNeighbourhood() {
        return neighbourhood;
    }

    public SelectorConfig setNeighbourhood(String neighbourhood) {
        this.neighbourhood = neighbourhood;
        return this;
    }

    public String getPrice() {
        return price;
    }

    public SelectorConfig setPrice(String price) {
        this.price = price;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public SelectorConfig setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getCurrency() {
        return currency;
    }

    public SelectorConfig setCurrency(String currency) {
        this.currency = currency;
        return this;
    }

    public String getSize() {
        return size;
    }

    public SelectorConfig setSize(String size) {
        this.size = size;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public SelectorConfig setUrl(String url) {
        this.url = url;
        return this;
    }
}
