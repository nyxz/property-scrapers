package me.nyxz.scrapers.realestate.util;

import me.nyxz.scrapers.realestate.dto.SelectorConfig;
import me.nyxz.scrapers.realestate.model.Property;
import org.jsoup.nodes.Element;
import org.springframework.util.StringUtils;

import java.util.Optional;

public class PropertyBuilder {

    private SelectorConfig selectorConfig;

    private PropertyBuilder(SelectorConfig selectorConfig) {
        this.selectorConfig = selectorConfig;
    }

    public static PropertyBuilder init(SelectorConfig selectorConfig) {
        return new PropertyBuilder(selectorConfig);
    }

    public Property fromElement(Element el) {
        return new Property()
                .setType(FieldUtil.by(el, selectorConfig.getType()))
                .setNeighbourhood(FieldUtil.by(el, selectorConfig.getNeighbourhood()))
                .setPriceText(FieldUtil.by(el, selectorConfig.getPrice()))
                .setPrice(toPrice(FieldUtil.by(el, selectorConfig.getPrice())))
                .setSize(FieldUtil.by(el, selectorConfig.getSize()))
                .setDescription(FieldUtil.by(el, selectorConfig.getDescription()))
                .setUrl(escapeUrl(FieldUtil.getHrefValue(el, selectorConfig.getUrl())));
    }

    private Long toPrice(String priceStr) {
        return StringUtils.isEmpty(priceStr) ? null : FieldUtil.toPrice(priceStr);
    }

    private String escapeUrl(String url) {
        return Optional.ofNullable(url).map(UrlUtil::escapeUrl).orElse(null);
    }
}
