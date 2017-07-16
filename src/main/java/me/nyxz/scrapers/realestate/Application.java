package me.nyxz.scrapers.realestate;

import me.nyxz.scrapers.realestate.dto.ScraperConfig;
import me.nyxz.scrapers.realestate.service.Scraper;
import me.nyxz.scrapers.realestate.service.ScraperConfigService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.FileSystemResource;

@SpringBootApplication
public class Application {

    private static final Logger LOG = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner run(ScraperConfigService configService, Scraper scraper) {
        return (args) -> {
            if (args.length != 1) {
                LOG.error("The programs takes exactly 1 argument - the query.");
                return;
            }
            String queryUrl = args[0];
            ScraperConfig config = configService.getConfigByQuery(queryUrl);
            scraper.printProperty(config);
        };
    }

    @Bean
    public PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        final String configLocation = System.getProperty("realestate.config.location");
        final PropertySourcesPlaceholderConfigurer config =
                new PropertySourcesPlaceholderConfigurer();
        config.setLocation(new FileSystemResource(configLocation));
        return config;
    }

}
