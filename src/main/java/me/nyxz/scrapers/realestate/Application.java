package me.nyxz.scrapers.realestate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
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

import java.io.UnsupportedEncodingException;

@SpringBootApplication
public class Application {

    private static final Logger LOG = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) throws UnsupportedEncodingException {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner run(ScraperConfigService configService, Scraper scraper) {
        return (String... args) -> {
            if (args.length != 1) {
                LOG.error("The programs takes exactly 1 argument - the query.");
                return;
            }
            final String queryUrl = args[0];
            ScraperConfig config = configService.getConfigByQuery(queryUrl);
            scraper.run(config);
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


    @Bean(name = BeanName.YAML_MAPPER)
    public ObjectMapper yamlMapper() {
        return new ObjectMapper(new YAMLFactory());
    }

    public final class BeanName {
        private BeanName() {
        }

        public static final String YAML_MAPPER = "yamlMapper";
    }
}
