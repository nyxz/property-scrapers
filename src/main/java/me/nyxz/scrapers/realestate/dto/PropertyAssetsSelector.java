package me.nyxz.scrapers.realestate.dto;

public class PropertyAssetsSelector {

    private String box;
    private SelectorConfig listSelectorConfig;
    private SelectorConfig selectorConfig;


    public String getBox() {
        return box;
    }

    public PropertyAssetsSelector setBox(String box) {
        this.box = box;
        return this;
    }

    public SelectorConfig getListSelectorConfig() {
        return listSelectorConfig;
    }

    public PropertyAssetsSelector setListSelectorConfig(SelectorConfig listSelectorConfig) {
        this.listSelectorConfig = listSelectorConfig;
        return this;
    }

    public SelectorConfig getSelectorConfig() {
        return selectorConfig;
    }

    public PropertyAssetsSelector setSelectorConfig(SelectorConfig selectorConfig) {
        this.selectorConfig = selectorConfig;
        return this;
    }
}
