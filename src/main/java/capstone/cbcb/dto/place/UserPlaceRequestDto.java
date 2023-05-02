package capstone.cbcb.dto.place;

import capstone.cbcb.domain.userplace.UserPlace;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserPlaceRequestDto {

    private String userplace_id;
    private int userplace_like;
    private String address;
    private int report;

    public UserPlace toEntity() {
        return UserPlace.builder()
                .userplace_id(userplace_id)
                .userplace_like(userplace_like)
                .address(address)
                .report(report)
                .build();
    }
}
