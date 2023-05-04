package capstone.cbcb.domain.place;


import capstone.cbcb.dto.place.PlaceResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PlaceRepository extends JpaRepository<Place, Long> {

    // 테마별
    @Query(value = "select * from place p where p.theme like %:theme% ", nativeQuery = true)
    List<Place> findByTheme(@Param("theme") String theme);

    // 지역별


    // 지역&테마별


    // 장소 상세정보



}
