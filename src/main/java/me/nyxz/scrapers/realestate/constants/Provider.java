package me.nyxz.scrapers.realestate.constants;

import java.util.Optional;

public enum Provider {
    IMOTEKA("imoteka.bg"),
    IMOT_BG("imot.bg");

    public static final String CONFIG_EXTENSION = "yml";
    public static final String CONFIG_DIR = "scraper";

    private String domain;

    Provider(String domain) {
        this.domain = domain;
    }

    public String getDomain() {
        return domain;
    }

    public static Optional<Provider> forDomain(String domain) {
        for (Provider provider : values()) {
            if (provider.getDomain().equalsIgnoreCase(domain)) {
                return Optional.of(provider);
            }
        }
        return Optional.empty();
    }

    public String getConfigFilename() {
        return String.format("%s/%s.%s", CONFIG_DIR, domain, CONFIG_EXTENSION);
    }
}
