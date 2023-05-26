package capstone.cbcb.domain.facility;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FacilityRepository extends JpaRepository<Facility, Long> {

    @Query(value = "SELECT f.amenities FROM facility f WHERE f.place_id_f = :placeId",nativeQuery = true)
    String findByPlaceIdF(@Param("placeId") String placeId);
}
