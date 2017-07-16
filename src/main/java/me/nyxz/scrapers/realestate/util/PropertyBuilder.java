package me.nyxz.scrapers.realestate.util;

import me.nyxz.scrapers.realestate.dto.SelectorConfig;
import me.nyxz.scrapers.realestate.model.Property;
import org.jsoup.nodes.Element;

public class PropertyBuilder {

    private SelectorConfig selectorConfig;

    private PropertyBuilder(SelectorConfig selectorConfig) {
        this.selectorConfig = selectorConfig;
    }

    public static PropertyBuilder init(SelectorConfig selectorConfig) {
        return new PropertyBuilder(selectorConfig);
    }

    public Property fromElement(Element el) {
        return new Property().setType(FieldUtil.by(el, selectorConfig.getType()))
                .setNeighbourhood(FieldUtil.by(el, selectorConfig.getNeighbourhood()))
                .setPriceText(FieldUtil.by(el, selectorConfig.getPrice()))
                .setPrice(FieldUtil.toPrice(FieldUtil.by(el, selectorConfig.getPrice())))
                .setSize(FieldUtil.by(el, selectorConfig.getSize()))
                .setDescription(FieldUtil.by(el, selectorConfig.getDescription()))
                .setUrl(FieldUtil.getHrefValue(el, selectorConfig.getUrl()));
    }
}
