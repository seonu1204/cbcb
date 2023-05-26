package capstone.cbcb.dto.place;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

// 장소 이름, 설명, 태그만 수정 가능
public class UserPlaceUpdateRequestDTO {
    private String userPlaceName;
    private String descript;
    private String tags;
}