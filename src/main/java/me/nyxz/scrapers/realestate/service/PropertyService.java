package me.nyxz.scrapers.realestate.service;

import me.nyxz.scrapers.realestate.model.Property;
import me.nyxz.scrapers.realestate.repo.PropertyRepository;
import me.nyxz.scrapers.realestate.util.PropertyMerger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PropertyService {

    @Autowired
    private PropertyRepository propertyRepository;

    public Property createOrUpdate(final Property property) {
        final Property nullableOldProperty = propertyRepository.findByUrl(property.getUrl());
        final Optional<Property> maybeProperty = Optional.ofNullable(nullableOldProperty);
        if (maybeProperty.isPresent()) {
            final Property oldProperty = maybeProperty.get();
            if (shouldUpdateProperty(oldProperty, property)) {
                return updateProperty(oldProperty, property);
            } else {
                return oldProperty;
            }
        } else {
            return createProperty(property);
        }
    }

    private Property createProperty(Property property) {
        return propertyRepository.save(property);
    }

    private Property updateProperty(Property oldProperty, Property newProperty) {
        final Property mergedProperty = PropertyMerger.merge(oldProperty, newProperty);
        return propertyRepository.save(mergedProperty);
    }

    private boolean shouldUpdateProperty(Property oldProperty, Property newProperty) {
        return oldProperty.hashCode() != newProperty.hashCode();
    }
}
