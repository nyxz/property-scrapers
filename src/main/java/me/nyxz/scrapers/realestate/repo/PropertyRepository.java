package me.nyxz.scrapers.realestate.repo;

import me.nyxz.scrapers.realestate.model.Property;
import org.springframework.data.repository.CrudRepository;

public interface PropertyRepository extends CrudRepository<Property, Long> {

    Property findByUrl(String url);
}
