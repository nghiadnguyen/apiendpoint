package vn.homecredit.smt.api.web.rest;

import vn.homecredit.smt.api.ApiendpointApp;
import vn.homecredit.smt.api.domain.ApiEndpoint;
import vn.homecredit.smt.api.repository.ApiEndpointRepository;
import vn.homecredit.smt.api.service.ApiEndpointService;
import vn.homecredit.smt.api.service.dto.ApiEndpointDTO;
import vn.homecredit.smt.api.service.mapper.ApiEndpointMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import vn.homecredit.smt.api.domain.enumeration.ApiEndpointVendor;
/**
 * Integration tests for the {@link ApiEndpointResource} REST controller.
 */
@SpringBootTest(classes = ApiendpointApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class ApiEndpointResourceIT {

    private static final ApiEndpointVendor DEFAULT_VENDOR = ApiEndpointVendor.INFOBIP;
    private static final ApiEndpointVendor UPDATED_VENDOR = ApiEndpointVendor.INFOBIP;

    private static final String DEFAULT_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_TYPE = "BBBBBBBBBB";

    private static final Boolean DEFAULT_ENABLED = false;
    private static final Boolean UPDATED_ENABLED = true;

    private static final Integer DEFAULT_RATE = 1;
    private static final Integer UPDATED_RATE = 2;

    private static final String DEFAULT_URL = "AAAAAAAAAA";
    private static final String UPDATED_URL = "BBBBBBBBBB";

    @Autowired
    private ApiEndpointRepository apiEndpointRepository;

    @Autowired
    private ApiEndpointMapper apiEndpointMapper;

    @Autowired
    private ApiEndpointService apiEndpointService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restApiEndpointMockMvc;

    private ApiEndpoint apiEndpoint;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ApiEndpoint createEntity(EntityManager em) {
        ApiEndpoint apiEndpoint = new ApiEndpoint()
            .vendor(DEFAULT_VENDOR)
            .type(DEFAULT_TYPE)
            .enabled(DEFAULT_ENABLED)
            .rate(DEFAULT_RATE)
            .url(DEFAULT_URL);
        return apiEndpoint;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ApiEndpoint createUpdatedEntity(EntityManager em) {
        ApiEndpoint apiEndpoint = new ApiEndpoint()
            .vendor(UPDATED_VENDOR)
            .type(UPDATED_TYPE)
            .enabled(UPDATED_ENABLED)
            .rate(UPDATED_RATE)
            .url(UPDATED_URL);
        return apiEndpoint;
    }

    @BeforeEach
    public void initTest() {
        apiEndpoint = createEntity(em);
    }

    @Test
    @Transactional
    public void createApiEndpoint() throws Exception {
        int databaseSizeBeforeCreate = apiEndpointRepository.findAll().size();
        // Create the ApiEndpoint
        ApiEndpointDTO apiEndpointDTO = apiEndpointMapper.toDto(apiEndpoint);
        restApiEndpointMockMvc.perform(post("/api/api-endpoints")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(apiEndpointDTO)))
            .andExpect(status().isCreated());

        // Validate the ApiEndpoint in the database
        List<ApiEndpoint> apiEndpointList = apiEndpointRepository.findAll();
        assertThat(apiEndpointList).hasSize(databaseSizeBeforeCreate + 1);
        ApiEndpoint testApiEndpoint = apiEndpointList.get(apiEndpointList.size() - 1);
        assertThat(testApiEndpoint.getVendor()).isEqualTo(DEFAULT_VENDOR);
        assertThat(testApiEndpoint.getType()).isEqualTo(DEFAULT_TYPE);
        assertThat(testApiEndpoint.isEnabled()).isEqualTo(DEFAULT_ENABLED);
        assertThat(testApiEndpoint.getRate()).isEqualTo(DEFAULT_RATE);
        assertThat(testApiEndpoint.getUrl()).isEqualTo(DEFAULT_URL);
    }

    @Test
    @Transactional
    public void createApiEndpointWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = apiEndpointRepository.findAll().size();

        // Create the ApiEndpoint with an existing ID
        apiEndpoint.setId(1L);
        ApiEndpointDTO apiEndpointDTO = apiEndpointMapper.toDto(apiEndpoint);

        // An entity with an existing ID cannot be created, so this API call must fail
        restApiEndpointMockMvc.perform(post("/api/api-endpoints")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(apiEndpointDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ApiEndpoint in the database
        List<ApiEndpoint> apiEndpointList = apiEndpointRepository.findAll();
        assertThat(apiEndpointList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = apiEndpointRepository.findAll().size();
        // set the field null
        apiEndpoint.setType(null);

        // Create the ApiEndpoint, which fails.
        ApiEndpointDTO apiEndpointDTO = apiEndpointMapper.toDto(apiEndpoint);


        restApiEndpointMockMvc.perform(post("/api/api-endpoints")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(apiEndpointDTO)))
            .andExpect(status().isBadRequest());

        List<ApiEndpoint> apiEndpointList = apiEndpointRepository.findAll();
        assertThat(apiEndpointList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEnabledIsRequired() throws Exception {
        int databaseSizeBeforeTest = apiEndpointRepository.findAll().size();
        // set the field null
        apiEndpoint.setEnabled(null);

        // Create the ApiEndpoint, which fails.
        ApiEndpointDTO apiEndpointDTO = apiEndpointMapper.toDto(apiEndpoint);


        restApiEndpointMockMvc.perform(post("/api/api-endpoints")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(apiEndpointDTO)))
            .andExpect(status().isBadRequest());

        List<ApiEndpoint> apiEndpointList = apiEndpointRepository.findAll();
        assertThat(apiEndpointList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkRateIsRequired() throws Exception {
        int databaseSizeBeforeTest = apiEndpointRepository.findAll().size();
        // set the field null
        apiEndpoint.setRate(null);

        // Create the ApiEndpoint, which fails.
        ApiEndpointDTO apiEndpointDTO = apiEndpointMapper.toDto(apiEndpoint);


        restApiEndpointMockMvc.perform(post("/api/api-endpoints")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(apiEndpointDTO)))
            .andExpect(status().isBadRequest());

        List<ApiEndpoint> apiEndpointList = apiEndpointRepository.findAll();
        assertThat(apiEndpointList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkUrlIsRequired() throws Exception {
        int databaseSizeBeforeTest = apiEndpointRepository.findAll().size();
        // set the field null
        apiEndpoint.setUrl(null);

        // Create the ApiEndpoint, which fails.
        ApiEndpointDTO apiEndpointDTO = apiEndpointMapper.toDto(apiEndpoint);


        restApiEndpointMockMvc.perform(post("/api/api-endpoints")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(apiEndpointDTO)))
            .andExpect(status().isBadRequest());

        List<ApiEndpoint> apiEndpointList = apiEndpointRepository.findAll();
        assertThat(apiEndpointList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllApiEndpoints() throws Exception {
        // Initialize the database
        apiEndpointRepository.saveAndFlush(apiEndpoint);

        // Get all the apiEndpointList
        restApiEndpointMockMvc.perform(get("/api/api-endpoints?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(apiEndpoint.getId().intValue())))
            .andExpect(jsonPath("$.[*].vendor").value(hasItem(DEFAULT_VENDOR.toString())))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE)))
            .andExpect(jsonPath("$.[*].enabled").value(hasItem(DEFAULT_ENABLED.booleanValue())))
            .andExpect(jsonPath("$.[*].rate").value(hasItem(DEFAULT_RATE)))
            .andExpect(jsonPath("$.[*].url").value(hasItem(DEFAULT_URL)));
    }
    
    @Test
    @Transactional
    public void getApiEndpoint() throws Exception {
        // Initialize the database
        apiEndpointRepository.saveAndFlush(apiEndpoint);

        // Get the apiEndpoint
        restApiEndpointMockMvc.perform(get("/api/api-endpoints/{id}", apiEndpoint.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(apiEndpoint.getId().intValue()))
            .andExpect(jsonPath("$.vendor").value(DEFAULT_VENDOR.toString()))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE))
            .andExpect(jsonPath("$.enabled").value(DEFAULT_ENABLED.booleanValue()))
            .andExpect(jsonPath("$.rate").value(DEFAULT_RATE))
            .andExpect(jsonPath("$.url").value(DEFAULT_URL));
    }
    @Test
    @Transactional
    public void getNonExistingApiEndpoint() throws Exception {
        // Get the apiEndpoint
        restApiEndpointMockMvc.perform(get("/api/api-endpoints/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateApiEndpoint() throws Exception {
        // Initialize the database
        apiEndpointRepository.saveAndFlush(apiEndpoint);

        int databaseSizeBeforeUpdate = apiEndpointRepository.findAll().size();

        // Update the apiEndpoint
        ApiEndpoint updatedApiEndpoint = apiEndpointRepository.findById(apiEndpoint.getId()).get();
        // Disconnect from session so that the updates on updatedApiEndpoint are not directly saved in db
        em.detach(updatedApiEndpoint);
        updatedApiEndpoint
            .vendor(UPDATED_VENDOR)
            .type(UPDATED_TYPE)
            .enabled(UPDATED_ENABLED)
            .rate(UPDATED_RATE)
            .url(UPDATED_URL);
        ApiEndpointDTO apiEndpointDTO = apiEndpointMapper.toDto(updatedApiEndpoint);

        restApiEndpointMockMvc.perform(put("/api/api-endpoints")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(apiEndpointDTO)))
            .andExpect(status().isOk());

        // Validate the ApiEndpoint in the database
        List<ApiEndpoint> apiEndpointList = apiEndpointRepository.findAll();
        assertThat(apiEndpointList).hasSize(databaseSizeBeforeUpdate);
        ApiEndpoint testApiEndpoint = apiEndpointList.get(apiEndpointList.size() - 1);
        assertThat(testApiEndpoint.getVendor()).isEqualTo(UPDATED_VENDOR);
        assertThat(testApiEndpoint.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testApiEndpoint.isEnabled()).isEqualTo(UPDATED_ENABLED);
        assertThat(testApiEndpoint.getRate()).isEqualTo(UPDATED_RATE);
        assertThat(testApiEndpoint.getUrl()).isEqualTo(UPDATED_URL);
    }

    @Test
    @Transactional
    public void updateNonExistingApiEndpoint() throws Exception {
        int databaseSizeBeforeUpdate = apiEndpointRepository.findAll().size();

        // Create the ApiEndpoint
        ApiEndpointDTO apiEndpointDTO = apiEndpointMapper.toDto(apiEndpoint);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restApiEndpointMockMvc.perform(put("/api/api-endpoints")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(apiEndpointDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ApiEndpoint in the database
        List<ApiEndpoint> apiEndpointList = apiEndpointRepository.findAll();
        assertThat(apiEndpointList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteApiEndpoint() throws Exception {
        // Initialize the database
        apiEndpointRepository.saveAndFlush(apiEndpoint);

        int databaseSizeBeforeDelete = apiEndpointRepository.findAll().size();

        // Delete the apiEndpoint
        restApiEndpointMockMvc.perform(delete("/api/api-endpoints/{id}", apiEndpoint.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ApiEndpoint> apiEndpointList = apiEndpointRepository.findAll();
        assertThat(apiEndpointList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
