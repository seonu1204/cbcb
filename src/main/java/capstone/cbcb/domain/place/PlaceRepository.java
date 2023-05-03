package capstone.cbcb.domain.place;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PlaceRepository extends JpaRepository<Place, Long> {


    @Query(value = "SELECT * FROM place, address WHERE place.place_id = address.place_id", nativeQuery = true)
    List<Object> findAllPlacesWithMatchingAddress();

    // Place table, Facility join
    @Query(value = "SELECT * FROM place, facility WHERE place.place_id = facility.place_id", nativeQuery = true)
    List<Object> findAllPlacesWithMatchingFacility();
}
