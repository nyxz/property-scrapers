package me.nyxz.scrapers.realestate.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import me.nyxz.scrapers.realestate.constants.Provider;
import me.nyxz.scrapers.realestate.dto.SelectorConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;

public class SelectorConfigUtil {

    private static final Logger LOG = LoggerFactory.getLogger(SelectorConfigUtil.class);

    public static SelectorConfig readForProvider(Provider provider, Class<?> clazz) {
        final String configFilename = provider.getConfigFilename();
        final ObjectMapper mapper = new ObjectMapper(new YAMLFactory());

        try (InputStream resourceAsStream = clazz.getClassLoader()
                .getResourceAsStream(configFilename)) {
            return mapper.readValue(resourceAsStream, SelectorConfig.class);
        } catch (Exception e) {
            final String message = "Couldn't read field path configuration.";
            LOG.error(message, e);
            throw new RuntimeException(message);
        }
    }
}
