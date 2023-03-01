package org.changsol.api.apps.users.repository;

import java.util.Optional;
import org.changsol.api.utils.bases.repository.ChangSolBaseRepository;
import org.changsol.api.apps.users.domain.Users;
import org.springframework.stereotype.Repository;

/**
 * Sample Master Repo Class
 */
@Repository
public interface UserRepository extends ChangSolBaseRepository<Users, Long> {
    Optional<Users> findByLoginId(String loginId);
}
