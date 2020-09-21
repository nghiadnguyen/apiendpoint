package vn.homecredit.smt.api.service;

import vn.homecredit.smt.api.service.dto.ApiEndpointDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link vn.homecredit.smt.api.domain.ApiEndpoint}.
 */
public interface ApiEndpointService {

    /**
     * Save a apiEndpoint.
     *
     * @param apiEndpointDTO the entity to save.
     * @return the persisted entity.
     */
    ApiEndpointDTO save(ApiEndpointDTO apiEndpointDTO);

    /**
     * Get all the apiEndpoints.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ApiEndpointDTO> findAll(Pageable pageable);


    /**
     * Get the "id" apiEndpoint.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ApiEndpointDTO> findOne(Long id);

    /**
     * Delete the "id" apiEndpoint.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
