package me.nyxz.scrapers.realestate.service;

import me.nyxz.scrapers.realestate.dto.ScraperConfig;
import me.nyxz.scrapers.realestate.dto.SelectorConfig;
import me.nyxz.scrapers.realestate.model.Property;
import me.nyxz.scrapers.realestate.util.PropertyBuilder;
import me.nyxz.scrapers.realestate.util.PropertyMerger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class Scraper {

    private static final Logger LOG = LoggerFactory.getLogger(Scraper.class);

    private static final int TIMEOUT = 5000;
    private static final long WAIT_TIME = 500;

    @Autowired
    private PropertyService propertyService;

    public void run(ScraperConfig config) throws IOException, InterruptedException {
        final List<Property> listSelectorProperties = getPropertiesFromList(config);
        final List<Property> fullProperties = getFullProperties(config, listSelectorProperties);

        fullProperties.forEach(propertyService::createOrUpdate);
    }

    private List<Property> getFullProperties(ScraperConfig config,
            List<Property> listSelectorProperties) throws InterruptedException, IOException {
        final List<Property> fullProperties = new ArrayList<>();
        for (Property property : listSelectorProperties) {
            Thread.sleep(WAIT_TIME);
            LOG.info("Processing property: " + property.getUrl());
            Document document = Jsoup.connect(property.getUrl()).timeout(TIMEOUT).get();
            PropertyBuilder propertyBuilder =
                    PropertyBuilder.init(config.getAssetsSelector().getSelectorConfig());
            Property mainProperty = propertyBuilder.fromElement(document);
            Property mergedProperty = PropertyMerger.merge(property, mainProperty);
            fullProperties.add(mergedProperty);
        }
        return fullProperties;
    }

    private List<Property> getPropertiesFromList(ScraperConfig config)
            throws InterruptedException, IOException {
        final String propertyBoxSelector = config.getAssetsSelector().getBox();
        final PropertyBuilder listPropertyBuilder = getListPropertyBuilder(config);
        final List<Property> listSelectorProperties = new ArrayList<>();
        boolean hasNextPage = true;
        int pageNum = 1;
        do {
            Thread.sleep(WAIT_TIME);
            final String pagedQuery = toPageQuery(config, pageNum++);
            LOG.info("Querying: " + pagedQuery);
            final Document document = Jsoup.connect(pagedQuery).timeout(TIMEOUT).get();
            final Elements elements = document.select(propertyBoxSelector);
            if (elements.isEmpty()) {
                break;
            }
            if (elements.size() < config.getAssetsSelector().getBoxPerPage()) {
                hasNextPage = false;
            }
            final List<Property> pageList = elements.stream()
                    .map(listPropertyBuilder::fromElement)
                    .collect(Collectors.toList());
            listSelectorProperties.addAll(pageList);
        } while (hasNextPage);

        return listSelectorProperties;
    }

    private PropertyBuilder getListPropertyBuilder(ScraperConfig config) {
        final SelectorConfig listSelectorConfig =
                config.getAssetsSelector().getListSelectorConfig();
        return PropertyBuilder.init(listSelectorConfig);
    }

    private String toPageQuery(ScraperConfig config, int pageNum) {
        return String.format("%s&%s=%d", config.getQuery(),
                config.getAssetsSelector().getPageQueryParam(), pageNum);
    }
}
