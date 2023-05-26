package capstone.cbcb.domain.coordinate.userPlaceCoordinate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;


@Transactional
public interface UserCoordinateRepository extends JpaRepository<UserCoordinate, Long> {

    @Modifying
    @Query(value = "delete from user_coordinate uc " +
            " where uc.userplace_id = :userPlaceId", nativeQuery = true)
    void delete(@Param("userPlaceId") int userPlaceId);
}