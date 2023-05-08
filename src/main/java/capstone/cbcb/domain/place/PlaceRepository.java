package capstone.cbcb.domain.place;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlaceRepository extends JpaRepository<Place, Long> {

    // 테마별
    List<Place> findByThemeContains(String theme);

    // 지역별
    List<Place> findByAddressContains(String loc);

    // 지역 & 테마별
    List<Place> findByAddressContainsAndThemeContains(String address, String theme);

    // 검색 ( 이름 or 주소 )
    List<Place> findByNameContainsOrAddressContains(String keyword);

    // 장소 상세정보
    
    // 장소 즐겨찾기 목록 조회
    
    // 사용자 등록 장소 목록 조회
    
    






}
