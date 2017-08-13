package me.nyxz.scrapers.realestate.dto;

public class ScraperConfig {

    private final String query;
    private final PropertyAssetsSelector assetsSelector;

    public ScraperConfig(String query, PropertyAssetsSelector assetsSelector) {
        this.query = query;
        this.assetsSelector = assetsSelector;
    }

    public String getQuery() {
        return query;
    }

    public PropertyAssetsSelector getAssetsSelector() {
        return assetsSelector;
    }
}
