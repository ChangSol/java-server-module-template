package org.changsol.api.apps.users.repository;

import java.util.Optional;
import org.changsol.api.apps.bases.repository.BaseRepository;
import org.changsol.api.apps.users.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * Sample Master Repo Class
 */
@Repository
public interface UserRepository extends BaseRepository<Users, Long> {
    Optional<Users> findByLoginId(String loginId);
}
