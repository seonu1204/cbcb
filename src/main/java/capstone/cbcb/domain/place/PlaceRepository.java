package capstone.cbcb.domain.place;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface PlaceRepository extends JpaRepository<Place, Long> {

    // 테마별
    List<Place> findByThemeContains(String theme);

    // 지역별
    List<Place> findByAddressContains(String loc);

    // 지역 & 테마별
    List<Place> findByAddressContainsAndThemeContains(String address, String theme);

    // 검색 ( 이름 or 주소 )
    @Query(value = "SELECT * FROM place p WHERE p.place_name like %:keyword% or " +
            " p.address like %:keyword%", nativeQuery = true)
    List<Place> findByNameContainsOrAddressContains(@Param(value = "keyword") String keyword);

    // 검색 필터링

    // 장소 상세정보
    @Query(value = "SELECT * FROM place p WHERE p.place_id = :id", nativeQuery = true)
    Place findById(@Param(value = "id") String id);


    // 검색 - 챗봇
    @Query(value =
            "SELECT * " +
                    "FROM place p " +
                    "WHERE (:gpe IS NULL OR p.address LIKE %:gpe%) " +
                    "AND (:city IS NULL OR p.address LIKE %:city%) " +
                    "AND (:season IS NULL OR p.season LIKE %:season%) " +
                    "AND (:theme IS NULL OR p.theme LIKE %:theme%) ", nativeQuery = true)
    List<Place> searchChatbot(@Param(value = "gpe") String gpe,
                              @Param(value = "city") String city,
                              @Param(value = "season") String season,
                              @Param(value = "theme") String theme);


    List<Place> findAll();

}