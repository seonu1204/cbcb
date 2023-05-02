package capstone.cbcb.dto.place;

import capstone.cbcb.domain.userplace.UserPlace;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserPlaceResponseDto {

    private String userplace_id;
    private int userplace_like;
    private String address;
    private int report;

    public UserPlaceResponseDto(UserPlace entity) {
        this.userplace_id = entity.getUserplace_id();
        this.userplace_like = entity.getUserplace_like();
        this.address = entity.getAddress();
        this.report = entity.getReport();
    }
}
