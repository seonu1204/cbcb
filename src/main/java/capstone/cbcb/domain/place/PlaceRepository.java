package capstone.cbcb.domain.place;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface PlaceRepository extends JpaRepository<Place, Long> {

    // 테마별
    List<Place> findByThemeContains(String theme);

    // 지역별
    List<Place> findByAddressContains(String loc);

    // 지역 & 테마별
    List<Place> findByAddressContainsAndThemeContains(String address, String theme);

    // 검색 ( 이름 or 주소 )
    @Query(value = "select * from place p where p.place_name like %:keyword% or " +
            " p.address like %:keyword%", nativeQuery = true)
    List<Place> findByNameContainsOrAddressContains(@Param(value = "keyword") String keyword);

    // 검색 필터링


    // 장소 상세정보
    @Query(value = "select * from place p where p.place_id = :id", nativeQuery = true)
    Place findById(@Param(value = "id") String id);

    // 장소 즐겨찾기 목록 조회

    // 사용자 등록 장소 목록 조회


//    // 검색 - 챗봇 (4개)
//    @Query(value = "" , nativeQuery = true)
//    List<Place> findBy4Keyword(@Param(value = "key") Map<String, String> key);
//
//    // 검색 - 챗봇 (3개)
//    @Query(value = "" , nativeQuery = true)
//    List<Place> findBy3Keyword(@Param(value = "key") Map<String, String> key);
//
//    // 검색 - 챗봇 (2개)
//    @Query(value = "" , nativeQuery = true)
//    List<Place> findBy2Keyword(@Param(value = "key") Map<String, String> key);
//
//    // 검색 - 챗봇 (1개)
//    @Query(value = "select * from place where " , nativeQuery = true)
//    List<Place> findBy1Keyword(@Param(value = "key") Map<String, String> key);

    List<Place> findAll();


}