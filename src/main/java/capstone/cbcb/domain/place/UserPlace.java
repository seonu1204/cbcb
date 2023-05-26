package capstone.cbcb.domain.place;


import capstone.cbcb.dto.place.UserPlaceUpdateRequestDTO;
import jakarta.persistence.*;

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

    @Column(name = "userplace_id")
    private int userPlaceId;
    private int user_id;
    private String address;
    @Column(name = "userplace_name")
    private String userPlaceName;
    private String descript;
    private String tags;
    private String latitude;
    private String longitude;
    @Column(name = "userplace_like")
    private int userPlaceLike;
    private int report;


    // 업데이트
    public void update(UserPlaceUpdateRequestDTO userPlaceUpdateRequestDTO) {
        this.userPlaceName = userPlaceUpdateRequestDTO.getUserPlaceName();
        this.descript = userPlaceUpdateRequestDTO.getDescript();
        this.tags = userPlaceUpdateRequestDTO.getTags();
    }

    public void setReport(int count) {
        this.report = count;
    }
    public void setUserPlaceLike(int count) {this.report = count;}

}