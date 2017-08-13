package me.nyxz.scrapers.realestate.util;

import me.nyxz.scrapers.realestate.model.Property;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.Optional;

public class FieldUtil {

    public static String by(Element element, String selector) {
        return Optional.ofNullable(selector)
                .map(s -> getText(element, s))
                .orElse(null);
    }

    private static String getText(Element element, String selector) {
        final Elements elements = element.select(selector);
        if (elements.isEmpty()) {
            return null;
        }
        final Element selectedElement = elements.get(0);
        return selectedElement.hasText() ? selectedElement.text() : selectedElement.html();
    }

    public static String getAttrValue(Element element, String selector, String attrName) {
        return Optional.ofNullable(selector)
                .map(s -> element.select(s).attr(attrName))
                .orElse(null);
    }

    public static String getHrefValue(Element element, String aTagSelector) {
        return getAttrValue(element, aTagSelector, "href");
    }

    public static Long toPrice(String priceStr) {
        int indexOfFirstCurrency = priceStr.trim().indexOf(Property.CURRENCY);
        return Long.valueOf(priceStr.substring(0, indexOfFirstCurrency).replace(" ", ""));
    }
}
