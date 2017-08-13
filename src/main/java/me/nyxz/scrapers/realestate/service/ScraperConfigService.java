package me.nyxz.scrapers.realestate.service;

import me.nyxz.scrapers.realestate.constants.Provider;
import me.nyxz.scrapers.realestate.dto.ScraperConfig;
import me.nyxz.scrapers.realestate.dto.SelectorConfig;
import me.nyxz.scrapers.realestate.util.UrlUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URISyntaxException;
import java.util.Optional;

@Service
public class ScraperConfigService {

    @Autowired
    private SelectorConfigReader selectorConfigReader;

    public ScraperConfig getConfigByQuery(String queryUrl) throws URISyntaxException {
        final String domainName = UrlUtil.getDomainName(queryUrl);
        final Optional<Provider> maybeProvider = Provider.forDomain(domainName);
        final Provider provider = maybeProvider.orElseThrow(
                () -> new IllegalStateException("Unknown provider " + domainName));
        final SelectorConfig selectorConfig =
                selectorConfigReader.readForProvider(provider, getClass());
        return new ScraperConfig(queryUrl, selectorConfig);
    }
}
