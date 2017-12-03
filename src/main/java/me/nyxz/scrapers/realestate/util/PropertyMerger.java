package me.nyxz.scrapers.realestate.util;

import me.nyxz.scrapers.realestate.model.Property;

import java.util.Optional;
import java.util.function.Function;

public class PropertyMerger {

    public static Property merge(Property base, Property diff) {
        return new PropertyMerger(base, diff).getMerge();
    }

    private Property base;
    private Property diff;
    private Property merge = new Property();

    /**
     * Merge 2 Properties - overrides the base with the assets from the diff.
     *
     * @param base - Property used as the base for the merge
     * @param diff - Property assets to override the base with
     */
    private PropertyMerger(Property base, Property diff) {
        this.base = base;
        this.diff = diff;
        merge.setId(mergedAsset(Property::getId));
        merge.setType(mergedAsset(Property::getType));
        merge.setNeighbourhood(mergedAsset(Property::getNeighbourhood));
        merge.setRawPrice(mergedAsset(Property::getRawPrice));
        merge.setPrice(mergedAsset(Property::getPrice));
        merge.setDescription(mergedAsset(Property::getDescription));
        merge.setSize(mergedAsset(Property::getSize));
        merge.setRawSize(mergedAsset(Property::getRawSize));
        merge.setUrl(mergedAsset(Property::getUrl));
    }

    private <T> T mergedAsset(Function<Property, T> fun) {
        return Optional.ofNullable(fun.apply(diff)).orElseGet(() -> fun.apply(base));
    }

    public Property getMerge() {
        return merge;
    }
}
