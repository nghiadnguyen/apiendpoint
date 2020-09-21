package vn.homecredit.smt.api.web.rest;

import vn.homecredit.smt.api.service.ApiEndpointService;
import vn.homecredit.smt.api.web.rest.errors.BadRequestAlertException;
import vn.homecredit.smt.api.service.dto.ApiEndpointDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link vn.homecredit.smt.api.domain.ApiEndpoint}.
 */
@RestController
@RequestMapping("/api")
public class ApiEndpointResource {

    private final Logger log = LoggerFactory.getLogger(ApiEndpointResource.class);

    private static final String ENTITY_NAME = "apiEndpoint";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ApiEndpointService apiEndpointService;

    public ApiEndpointResource(ApiEndpointService apiEndpointService) {
        this.apiEndpointService = apiEndpointService;
    }

    /**
     * {@code POST  /api-endpoints} : Create a new apiEndpoint.
     *
     * @param apiEndpointDTO the apiEndpointDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new apiEndpointDTO, or with status {@code 400 (Bad Request)} if the apiEndpoint has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/api-endpoints")
    public ResponseEntity<ApiEndpointDTO> createApiEndpoint(@Valid @RequestBody ApiEndpointDTO apiEndpointDTO) throws URISyntaxException {
        log.debug("REST request to save ApiEndpoint : {}", apiEndpointDTO);
        if (apiEndpointDTO.getId() != null) {
            throw new BadRequestAlertException("A new apiEndpoint cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ApiEndpointDTO result = apiEndpointService.save(apiEndpointDTO);
        return ResponseEntity.created(new URI("/api/api-endpoints/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /api-endpoints} : Updates an existing apiEndpoint.
     *
     * @param apiEndpointDTO the apiEndpointDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated apiEndpointDTO,
     * or with status {@code 400 (Bad Request)} if the apiEndpointDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the apiEndpointDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/api-endpoints")
    public ResponseEntity<ApiEndpointDTO> updateApiEndpoint(@Valid @RequestBody ApiEndpointDTO apiEndpointDTO) throws URISyntaxException {
        log.debug("REST request to update ApiEndpoint : {}", apiEndpointDTO);
        if (apiEndpointDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ApiEndpointDTO result = apiEndpointService.save(apiEndpointDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, apiEndpointDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /api-endpoints} : get all the apiEndpoints.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of apiEndpoints in body.
     */
    @GetMapping("/api-endpoints")
    public ResponseEntity<List<ApiEndpointDTO>> getAllApiEndpoints(Pageable pageable) {
        log.debug("REST request to get a page of ApiEndpoints");
        Page<ApiEndpointDTO> page = apiEndpointService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /api-endpoints/:id} : get the "id" apiEndpoint.
     *
     * @param id the id of the apiEndpointDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the apiEndpointDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/api-endpoints/{id}")
    public ResponseEntity<ApiEndpointDTO> getApiEndpoint(@PathVariable Long id) {
        log.debug("REST request to get ApiEndpoint : {}", id);
        Optional<ApiEndpointDTO> apiEndpointDTO = apiEndpointService.findOne(id);
        return ResponseUtil.wrapOrNotFound(apiEndpointDTO);
    }

    /**
     * {@code DELETE  /api-endpoints/:id} : delete the "id" apiEndpoint.
     *
     * @param id the id of the apiEndpointDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/api-endpoints/{id}")
    public ResponseEntity<Void> deleteApiEndpoint(@PathVariable Long id) {
        log.debug("REST request to delete ApiEndpoint : {}", id);
        apiEndpointService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
