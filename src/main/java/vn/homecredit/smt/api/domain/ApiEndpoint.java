package vn.homecredit.smt.api.domain;


import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

import vn.homecredit.smt.api.domain.enumeration.ApiEndpointVendor;

/**
 * A ApiEndpoint.
 */
@Entity
@Table(name = "api_endpoint")
public class ApiEndpoint implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "vendor")
    private ApiEndpointVendor vendor;

    @NotNull
    @Size(max = 50)
    @Column(name = "type", length = 50, nullable = false)
    private String type;

    @NotNull
    @Column(name = "enabled", nullable = false)
    private Boolean enabled;

    @NotNull
    @Column(name = "rate", nullable = false)
    private Integer rate;

    @NotNull
    @Size(max = 200)
    @Column(name = "url", length = 200, nullable = false)
    private String url;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ApiEndpointVendor getVendor() {
        return vendor;
    }

    public ApiEndpoint vendor(ApiEndpointVendor vendor) {
        this.vendor = vendor;
        return this;
    }

    public void setVendor(ApiEndpointVendor vendor) {
        this.vendor = vendor;
    }

    public String getType() {
        return type;
    }

    public ApiEndpoint type(String type) {
        this.type = type;
        return this;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean isEnabled() {
        return enabled;
    }

    public ApiEndpoint enabled(Boolean enabled) {
        this.enabled = enabled;
        return this;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Integer getRate() {
        return rate;
    }

    public ApiEndpoint rate(Integer rate) {
        this.rate = rate;
        return this;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }

    public String getUrl() {
        return url;
    }

    public ApiEndpoint url(String url) {
        this.url = url;
        return this;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ApiEndpoint)) {
            return false;
        }
        return id != null && id.equals(((ApiEndpoint) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ApiEndpoint{" +
            "id=" + getId() +
            ", vendor='" + getVendor() + "'" +
            ", type='" + getType() + "'" +
            ", enabled='" + isEnabled() + "'" +
            ", rate=" + getRate() +
            ", url='" + getUrl() + "'" +
            "}";
    }
}
