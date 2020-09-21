package vn.homecredit.smt.api.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import vn.homecredit.smt.api.web.rest.TestUtil;

public class ApiEndpointDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ApiEndpointDTO.class);
        ApiEndpointDTO apiEndpointDTO1 = new ApiEndpointDTO();
        apiEndpointDTO1.setId(1L);
        ApiEndpointDTO apiEndpointDTO2 = new ApiEndpointDTO();
        assertThat(apiEndpointDTO1).isNotEqualTo(apiEndpointDTO2);
        apiEndpointDTO2.setId(apiEndpointDTO1.getId());
        assertThat(apiEndpointDTO1).isEqualTo(apiEndpointDTO2);
        apiEndpointDTO2.setId(2L);
        assertThat(apiEndpointDTO1).isNotEqualTo(apiEndpointDTO2);
        apiEndpointDTO1.setId(null);
        assertThat(apiEndpointDTO1).isNotEqualTo(apiEndpointDTO2);
    }
}
