package capstone.cbcb.dto.place;

import capstone.cbcb.domain.place.UserPlace;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserPlaceRequestDto {

    private String userPlaceId;
    private int userPlaceLike;
    private String address;
    private int report;

    public UserPlaceRequestDto(UserPlace entity) {
        this.userPlaceId = entity.getUserPlaceId();
        this.userPlaceLike = entity.getUserPlaceLike();
        this.address = entity.getAddress();
        this.report = entity.getReport();
    }

}
