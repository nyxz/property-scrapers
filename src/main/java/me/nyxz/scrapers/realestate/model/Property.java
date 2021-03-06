package me.nyxz.scrapers.realestate.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Optional;

@Entity
@Table(name = "property", schema = "realestate",
        indexes = {@Index(name = "url_index", columnList = "url")})
public class Property {

    public static final String CURRENCY = "EUR";
    public static final int DESCRIPTION_LEN = 10_000;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "type")
    private String type;

    @Column(name = "neighbourhood")
    private String neighbourhood;

    @Column(name = "price")
    private Long price;

    @Column(name = "raw_price")
    private String rawPrice;

    @Column(name = "description", length = DESCRIPTION_LEN)
    private String description;

    @Column(name = "size")
    private Long size;

    @Column(name = "raw_size")
    private String rawSize;

    @Column(name = "url", length = 2_083, unique = true)
    private String url;

    @Column(name = "date_created")
    private Date dateCreated;

    @Column(name = "date_modified")
    private Date dateModified;

    @PrePersist
    public void prePersist() {
        final Date now = new Date();
        dateCreated = now;
        dateModified = now;
        ensureDescriptionLenght();
    }

    @PreUpdate
    public void preUpdate() {
        dateModified = new Date();
        ensureDescriptionLenght();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public Property setType(String type) {
        this.type = type;
        return this;
    }

    public String getNeighbourhood() {
        return neighbourhood;
    }

    public Property setNeighbourhood(String neighbourhood) {
        this.neighbourhood = neighbourhood;
        return this;
    }

    public Long getPrice() {
        return price;
    }

    public Property setPrice(Long price) {
        this.price = price;
        return this;
    }

    public String getRawPrice() {
        return rawPrice;
    }

    public Property setRawPrice(String rawPrice) {
        this.rawPrice = rawPrice;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Property setDescription(String description) {
        this.description = description;
        return this;
    }

    public Long getSize() {
        return size;
    }

    public Property setSize(Long size) {
        this.size = size;
        return this;
    }

    public String getRawSize() {
        return rawSize;
    }

    public Property setRawSize(String rawSize) {
        this.rawSize = rawSize;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public Property setUrl(String url) {
        this.url = url;
        return this;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getDateModified() {
        return dateModified;
    }

    public void setDateModified(Date dateModified) {
        this.dateModified = dateModified;
    }

    @Override
    public String toString() {
        return String.format("%-30s | %-16s | %-16s | %-20s | %-8d%s | %-24s | %s | %s | %s | %s ",
                getType(),
                getSize(),
                getRawSize(),
                getNeighbourhood(),
                getPrice(),
                CURRENCY,
                getRawPrice(),
                getUrl(),
                getDescription(),
                toIsoDateString(getDateCreated()),
                toIsoDateString(getDateModified()));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Property property = (Property) o;

        if (getType() != null ? !getType().equals(property.getType()) :
                property.getType() != null) {
            return false;
        }
        if (getNeighbourhood() != null ? !getNeighbourhood().equals(property.getNeighbourhood()) :
                property.getNeighbourhood() != null) {
            return false;
        }
        if (getRawPrice() != null ? !getRawPrice().equals(property.getRawPrice()) :
                property.getRawPrice() != null) {
            return false;
        }
        if (getDescription() != null ? !getDescription().equals(property.getDescription()) :
                property.getDescription() != null) {
            return false;
        }
        if (getSize() != null ? !getSize().equals(property.getSize()) :
                property.getSize() != null) {
            return false;
        }
        return getUrl().equals(property.getUrl());
    }

    @Override
    public int hashCode() {
        int result = getType() != null ? getType().hashCode() : 0;
        result = 31 * result + (getNeighbourhood() != null ? getNeighbourhood().hashCode() : 0);
        result = 31 * result + (getRawPrice() != null ? getRawPrice().hashCode() : 0);
        result = 31 * result + (getDescription() != null ? getDescription().hashCode() : 0);
        result = 31 * result + (getSize() != null ? getSize().hashCode() : 0);
        result = 31 * result + getUrl().hashCode();
        return result;
    }

    private String toIsoDateString(Date date) {
        return Optional.ofNullable(date)
                .map(Date::toInstant)
                .map(DateTimeFormatter.ISO_DATE::format)
                .orElse("<not-set>");
    }

    private void ensureDescriptionLenght() {
        if (description != null && description.length() > DESCRIPTION_LEN) {
            description = description.substring(0, DESCRIPTION_LEN);
        }
    }
}
