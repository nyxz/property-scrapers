package me.nyxz.scrapers.realestate.service;

import me.nyxz.scrapers.realestate.model.Property;
import me.nyxz.scrapers.realestate.repo.PropertyRepository;
import me.nyxz.scrapers.realestate.util.PropertyMerger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@Service
public class PropertyService {

    private static final int BATCH_SIZE = 50;

    @Autowired
    private PropertyRepository propertyRepository;

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public void createOrUpdateBatched(List<Property> properties) {
        final int size = properties.size();
        IntStream.range(0, (size + BATCH_SIZE - 1) / BATCH_SIZE)
                .mapToObj(idx -> properties
                        .subList(idx * BATCH_SIZE, Math.min(size, (idx + 1) * BATCH_SIZE)))
                .parallel()
                .forEach(this::createOrUpdateMany);
    }

    private void createOrUpdateMany(List<Property> properties) {
        properties.forEach(this::createOrUpdate);
        em.flush();
        em.clear();
    }

    @Transactional
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
