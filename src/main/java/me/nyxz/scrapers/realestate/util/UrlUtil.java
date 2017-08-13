package me.nyxz.scrapers.realestate.util;

import me.nyxz.scrapers.realestate.Application;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class UrlUtil {

    private static final Logger LOG = LoggerFactory.getLogger(Application.class);

    public static String getDomainName(String url) throws URISyntaxException {
        URI uri = new URI(url);
        String domain = uri.getHost();
        return domain.startsWith("www.") ? domain.substring(4) : domain;
    }

    public static String escapeUrl(String urlStr) {
        try {
            final URL url = new URL(urlStr);
            final URI uri = new URI(
                    url.getProtocol(),
                    url.getUserInfo(),
                    url.getHost(),
                    url.getPort(),
                    url.getPath(),
                    url.getQuery(),
                    url.getRef());
            return uri.toASCIIString();
        } catch (URISyntaxException | MalformedURLException e) {
            LOG.error("Bad URL: " + urlStr, e);
            throw new RuntimeException("Bad URL " + urlStr);
        }
    }
}
