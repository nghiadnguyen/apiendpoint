package vn.homecredit.smt.api.service.impl;

import vn.homecredit.smt.api.service.ApiEndpointService;
import vn.homecredit.smt.api.domain.ApiEndpoint;
import vn.homecredit.smt.api.repository.ApiEndpointRepository;
import vn.homecredit.smt.api.service.dto.ApiEndpointDTO;
import vn.homecredit.smt.api.service.mapper.ApiEndpointMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link ApiEndpoint}.
 */
@Service
@Transactional
public class ApiEndpointServiceImpl implements ApiEndpointService {

    private final Logger log = LoggerFactory.getLogger(ApiEndpointServiceImpl.class);

    private final ApiEndpointRepository apiEndpointRepository;

    private final ApiEndpointMapper apiEndpointMapper;

    public ApiEndpointServiceImpl(ApiEndpointRepository apiEndpointRepository, ApiEndpointMapper apiEndpointMapper) {
        this.apiEndpointRepository = apiEndpointRepository;
        this.apiEndpointMapper = apiEndpointMapper;
    }

    @Override
    public ApiEndpointDTO save(ApiEndpointDTO apiEndpointDTO) {
        log.debug("Request to save ApiEndpoint : {}", apiEndpointDTO);
        ApiEndpoint apiEndpoint = apiEndpointMapper.toEntity(apiEndpointDTO);
        apiEndpoint = apiEndpointRepository.save(apiEndpoint);
        return apiEndpointMapper.toDto(apiEndpoint);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ApiEndpointDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ApiEndpoints");
        return apiEndpointRepository.findAll(pageable)
            .map(apiEndpointMapper::toDto);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<ApiEndpointDTO> findOne(Long id) {
        log.debug("Request to get ApiEndpoint : {}", id);
        return apiEndpointRepository.findById(id)
            .map(apiEndpointMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete ApiEndpoint : {}", id);
        apiEndpointRepository.deleteById(id);
    }
}
