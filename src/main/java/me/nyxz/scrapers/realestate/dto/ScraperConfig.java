package me.nyxz.scrapers.realestate.dto;

public class ScraperConfig {

    private final String query;
    private final SelectorConfig selectorConfig;

    public ScraperConfig(String query, SelectorConfig selectorConfig) {
        this.query = query;
        this.selectorConfig = selectorConfig;
    }

    public String getQuery() {
        return query;
    }

    public SelectorConfig getSelectorConfig() {
        return selectorConfig;
    }
}
