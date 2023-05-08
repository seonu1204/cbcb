package capstone.cbcb.domain.place;


import capstone.cbcb.dto.place.UserPlaceResponseDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Entity
public class UserPlace {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String userPlaceId;

    private int userPlaceLike;
    private String address;
    private int report;


    // 업데이트 ??
    public void update(UserPlaceResponseDto userPlaceResponseDto) {
        this.userPlaceId = userPlaceResponseDto.getUserPlaceId();
        this.userPlaceLike = userPlaceResponseDto.getUserPlaceLike();
        this.address = userPlaceResponseDto.getAddress();
        this.report = userPlaceResponseDto.getReport();
    }
}