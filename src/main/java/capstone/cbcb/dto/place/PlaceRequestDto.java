package capstone.cbcb.dto.place;

import capstone.cbcb.domain.place.Place;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
// 엔티티를 보호하기 위해 한번더 감싸는 dto인데, 그중에서도 클라이언트로부터 받은 데이터를 담아 요청처리를 담당하는 dto
public class PlaceRequestDto {

    private String place_id;
    private String placeName;
    private String address;
    private String contact;
    private String camp_env;
    private String season;
    private String operation_day;
    private String page_url;
    private String reservation;
    private String facils;
    private String theme;
    private int place_like;

    public PlaceRequestDto(Place entity) {
        this.place_id = entity.getPlace_id();
        this.placeName = entity.getPlaceName();
        this.address = entity.getAddress();
        this.contact = entity.getContact();
        this.camp_env = entity.getCamp_env();
        this.season = entity.getSeason();
        this.operation_day = entity.getOperation_day();
        this.page_url = entity.getPage_url();
        this.reservation = entity.getReservation();
        this.facils = entity.getFacils();
        this.theme = entity.getTheme();
        this.place_like = entity.getPlace_like();
    }

}
