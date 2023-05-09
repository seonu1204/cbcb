package capstone.cbcb.dto.place;

import capstone.cbcb.domain.place.UserPlace;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserPlaceResponseDto {

    private String userPlaceId;
    private int userPlaceLike;
    private String address;
    private int report;

    public UserPlaceResponseDto(UserPlace entity) {
        this.userPlaceId = entity.getUserPlaceId();
        this.userPlaceLike = entity.getUserPlaceLike();
        this.address = entity.getAddress();
        this.report = entity.getReport();
    }
}
