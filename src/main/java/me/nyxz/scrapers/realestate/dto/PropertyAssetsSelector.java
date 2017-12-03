package me.nyxz.scrapers.realestate.dto;

public class PropertyAssetsSelector {

    private String box;
    private int boxPerPage;
    private String pageQueryParam;
    private SelectorConfig listSelectorConfig;
    private SelectorConfig selectorConfig;

    public String getPageQueryParam() {
        return pageQueryParam;
    }

    public void setPageQueryParam(String pageQueryParam) {
        this.pageQueryParam = pageQueryParam;
    }

    public String getBox() {
        return box;
    }

    public PropertyAssetsSelector setBox(String box) {
        this.box = box;
        return this;
    }

    public int getBoxPerPage() {
        return boxPerPage;
    }

    public PropertyAssetsSelector setBoxPerPage(int boxPerPage) {
        this.boxPerPage = boxPerPage;
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
