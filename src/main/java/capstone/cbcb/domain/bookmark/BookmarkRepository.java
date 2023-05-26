package capstone.cbcb.domain.bookmark;

import capstone.cbcb.domain.place.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {

    // 사용자가 북마크한 장소 목록 id 리스트 user_id로 찾기
    @Query(value = "SELECT place_id FROM bookmark b " +
            "WHERE b.user_id = :UserId", nativeQuery = true)
    List<String> findByUserId(@Param(value = "UserId") int UserId);

    Bookmark findByPlaceId(String place_id);


}