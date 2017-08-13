package me.nyxz.scrapers.realestate.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.nyxz.scrapers.realestate.Application;
import me.nyxz.scrapers.realestate.constants.Provider;
import me.nyxz.scrapers.realestate.dto.SelectorConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.InputStream;

@Service
public class SelectorConfigReader {

    private static final Logger LOG = LoggerFactory.getLogger(SelectorConfigReader.class);

    @Autowired
    @Qualifier(Application.BeanName.YAML_MAPPER)
    private ObjectMapper yamlMapper;

    public SelectorConfig readForProvider(Provider provider, Class<?> clazz) {
        final String configFilename = provider.getConfigFilename();
        try (InputStream resourceAsStream = clazz.getClassLoader()
                .getResourceAsStream(configFilename)) {
            return yamlMapper.readValue(resourceAsStream, SelectorConfig.class);
        } catch (Exception e) {
            final String message = "Couldn't read field path configuration.";
            LOG.error(message, e);
            throw new RuntimeException(message);
        }
    }
}