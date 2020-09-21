package vn.homecredit.smt.api.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import vn.homecredit.smt.api.domain.enumeration.ApiEndpointVendor;

/**
 * A DTO for the {@link vn.homecredit.smt.api.domain.ApiEndpoint} entity.
 */
public class ApiEndpointDTO implements Serializable {
    
    private Long id;

    private ApiEndpointVendor vendor;

    @NotNull
    @Size(max = 50)
    private String type;

    @NotNull
    private Boolean enabled;

    @NotNull
    private Integer rate;

    @NotNull
    @Size(max = 200)
    private String url;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ApiEndpointVendor getVendor() {
        return vendor;
    }

    public void setVendor(ApiEndpointVendor vendor) {
        this.vendor = vendor;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ApiEndpointDTO)) {
            return false;
        }

        return id != null && id.equals(((ApiEndpointDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ApiEndpointDTO{" +
            "id=" + getId() +
            ", vendor='" + getVendor() + "'" +
            ", type='" + getType() + "'" +
            ", enabled='" + isEnabled() + "'" +
            ", rate=" + getRate() +
            ", url='" + getUrl() + "'" +
            "}";
    }
}
