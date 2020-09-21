package vn.homecredit.smt.api.service.mapper;


import vn.homecredit.smt.api.domain.*;
import vn.homecredit.smt.api.service.dto.ApiEndpointDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ApiEndpoint} and its DTO {@link ApiEndpointDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ApiEndpointMapper extends EntityMapper<ApiEndpointDTO, ApiEndpoint> {



    default ApiEndpoint fromId(Long id) {
        if (id == null) {
            return null;
        }
        ApiEndpoint apiEndpoint = new ApiEndpoint();
        apiEndpoint.setId(id);
        return apiEndpoint;
    }
}
