package vn.homecredit.smt.api.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import vn.homecredit.smt.api.web.rest.TestUtil;

public class ApiEndpointTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ApiEndpoint.class);
        ApiEndpoint apiEndpoint1 = new ApiEndpoint();
        apiEndpoint1.setId(1L);
        ApiEndpoint apiEndpoint2 = new ApiEndpoint();
        apiEndpoint2.setId(apiEndpoint1.getId());
        assertThat(apiEndpoint1).isEqualTo(apiEndpoint2);
        apiEndpoint2.setId(2L);
        assertThat(apiEndpoint1).isNotEqualTo(apiEndpoint2);
        apiEndpoint1.setId(null);
        assertThat(apiEndpoint1).isNotEqualTo(apiEndpoint2);
    }
}
