package capstone.cbcb.dto.place;

import capstone.cbcb.domain.place.UserPlace;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserPlaceRequestDto {

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

    public UserPlaceRequestDto(UserPlace entity) {
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


    // 디비에 저장하기 위해 requestDTO 를 엔티티로 변환
    public UserPlace toEntity() {
        return UserPlace.builder()
                .userPlaceId(userPlaceId)
                .user_id(user_id)
                .address(address)
                .userPlaceName(userPlaceName)
                .descript(descript)
                .tags(tags)
                .latitude(latitude)
                .longitude(longitude)
                .userPlaceLike(userPlaceLike)
                .report(report)
                .build();
    }
}