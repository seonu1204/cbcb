package capstone.cbcb.domain.coordinate.placeCoordinate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CoordinateRepository extends JpaRepository<Coordinate, Long> {

    @Query(value = "select * from coordinate c where c.place_id = :placeId", nativeQuery = true)
    Coordinate findByPlaceId(@Param("placeId") String placeId);
}
