package me.nyxz.scrapers.realestate.util;

import me.nyxz.scrapers.realestate.model.Property;
import org.jsoup.nodes.Element;

import java.util.Optional;

public class FieldUtil {

    public static String by(Element element, String selector) {
        return Optional.ofNullable(selector).map(s -> element.select(s).text()).orElse(null);
    }

    public static String getAttrValue(Element element, String selector, String attrName) {
        return Optional.ofNullable(selector)
                .map(s -> element.select(s).attr(attrName))
                .orElse(null);
    }

    public static String getHrefValue(Element element, String aTagSelector) {
        return getAttrValue(element, aTagSelector, "href");
    }

    public static int toPrice(String priceStr) {
        int indexOfFirstCurrency = priceStr.trim().indexOf(Property.CURRENCY);
        return Integer.valueOf(priceStr.substring(0, indexOfFirstCurrency).replace(" ", ""));
    }
}
