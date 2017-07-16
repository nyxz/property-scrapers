package me.nyxz.scrapers.realestate.service;

import me.nyxz.scrapers.realestate.dto.ScraperConfig;
import me.nyxz.scrapers.realestate.repo.PropertyRepository;
import me.nyxz.scrapers.realestate.util.PropertyBuilder;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class Scraper {

    @Autowired
    private PropertyRepository propertyRepository;

    public void printProperty(ScraperConfig config) throws IOException {
        final Document document = Jsoup.connect(config.getQuery()).get();
        final PropertyBuilder propertyBuilder = PropertyBuilder.init(config.getSelectorConfig());
        final Elements elements = document.select(config.getSelectorConfig().getBox());
        elements.stream()
                .map(propertyBuilder::fromElement)
                .forEach(propertyRepository::save);
    }
}
