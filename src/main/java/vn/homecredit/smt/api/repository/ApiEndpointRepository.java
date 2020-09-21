package vn.homecredit.smt.api.repository;

import vn.homecredit.smt.api.domain.ApiEndpoint;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the ApiEndpoint entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ApiEndpointRepository extends JpaRepository<ApiEndpoint, Long> {
}
