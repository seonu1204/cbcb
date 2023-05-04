package capstone.cbcb.domain.place;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PlaceRepository extends JpaRepository<Place, Long> {

    // 테마별
    @Query(value = "select * from place p where p.theme like %:theme% ", nativeQuery = true)
    List<Place> findByTheme(@Param("theme") String theme);


    // 지역별
    @Query(value = "select * from place p where p.address like %:loc% ", nativeQuery = true)
    List<Place> findByAddress(@Param("loc") String loc);


    // 지역&테마별
    @Query(value = "select * from place p where p.address like %:loc% and p.theme like %:theme% ", nativeQuery = true)
    List<Place> findByAddressAndTheme(@Param("loc") String loc, @Param("theme") String theme);


    // 장소 상세정보




    // 키워드 검색


}