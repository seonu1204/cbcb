package capstone.cbcb.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Boolean existsByEmail(String email);

    Optional<User> findByEmail(String email);

    @Query(value = "SELECT * FROM user WHERE user.user_id = :userId", nativeQuery = true)
    User findById(@Param("userId") int userId);


}
