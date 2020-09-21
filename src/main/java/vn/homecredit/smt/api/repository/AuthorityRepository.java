package vn.homecredit.smt.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.homecredit.smt.api.domain.Authority;

/**
 * Spring Data JPA repository for the {@link Authority} entity.
 */
public interface AuthorityRepository extends JpaRepository<Authority, String> {}
