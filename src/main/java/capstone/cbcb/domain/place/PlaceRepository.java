package capstone.cbcb.domain.place;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PlaceRepository extends JpaRepository<Place, Long> {

    // 테마별
    //@Query(value = "select * from place p where p.theme like %:theme% ", nativeQuery = true)
    List<Place> findByThemeContains(@Param("theme") String theme);

    // 지역별
   // @Query(value = "select * from place p where p.loc like %:loc% ", nativeQuery = true)
    List<Place> findByAddressContains(@Param("loc") String loc);

    // 지역 & 테마별
    //@Query(value = "select * from place p where p.theme like %:theme% and p.loc like %:loc%", nativeQuery = true)
    List<Place> findByAddressContainsAndThemeContains(@Param("theme") String address, @Param("loc") String theme);

    // 검색 ( 이름 or 주소 )
    //@Query(value = "select * from place p where p.place_name like %:keyword% and p.address like %:keyword%", nativeQuery = true)
    List<Place> findByPlace_nameContainsOrAddressContains(@Param("keyword") String keyword);









    // 장소 상세정보
    
    // 장소 즐겨찾기 목록 조회
    
    // 사용자 등록 장소 목록 조회
    
    






}