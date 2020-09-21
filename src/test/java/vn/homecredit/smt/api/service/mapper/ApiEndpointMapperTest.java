package vn.homecredit.smt.api.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class ApiEndpointMapperTest {

    private ApiEndpointMapper apiEndpointMapper;

    @BeforeEach
    public void setUp() {
        apiEndpointMapper = new ApiEndpointMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(apiEndpointMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(apiEndpointMapper.fromId(null)).isNull();
    }
}
