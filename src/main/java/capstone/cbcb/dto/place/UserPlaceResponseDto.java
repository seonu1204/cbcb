package capstone.cbcb.dto.place;

import capstone.cbcb.domain.place.UserPlace;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter

@NoArgsConstructor
public class UserPlaceResponseDto {

    private int userPlaceId;
    private int user_id;
    private String address;
    private String userPlaceName;
    private String descript;
    private String tags;
    private String latitude;
    private String longitude;
    private int userPlaceLike;
    private int report;
    
    
    // 추가
    private String userEmail;


    public UserPlaceResponseDto(UserPlace entity) {
        this.userPlaceId = entity.getUserPlaceId();
        this.user_id = entity.getUser_id();
        this.address = entity.getAddress();
        this.userPlaceName = entity.getUserPlaceName();
        this.descript = entity.getDescript();
        this.tags = entity.getTags();
        this.latitude = entity.getLatitude();
        this.longitude = entity.getLongitude();
        this.userPlaceLike = entity.getUserPlaceLike();
        this.report = entity.getReport();

    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
}
