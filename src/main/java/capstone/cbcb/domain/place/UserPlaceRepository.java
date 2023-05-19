package capstone.cbcb.domain.place;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface UserPlaceRepository extends JpaRepository<UserPlace, Long> {

      // 사용자 id로 장소 조회 (마이페이지용)
      @Query(value = "select * from user_place p " +
              "where p.user_id = :userId", nativeQuery = true)
      List<UserPlace> findByIdList(@Param("userId") int userId);


      // 사용자 장소 1개 조회
      UserPlace findByUserPlaceId(@Param("userPlaceId") int userPlaceId);


      // 좌표값으로 장소 조회
      @Query(value =
              "select * from user_place p " +
              " where exists " +
                  " ( select * " +
                  " from user_place p " +
                  " where p.latitude = :lat and p.longitude = :lon)", nativeQuery = true)
      UserPlace existsByCoordinate(@Param("lat") String lat, @Param("lon") String lon);
}
