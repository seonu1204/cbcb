package capstone.cbcb.dto.place;

import capstone.cbcb.domain.place.Place;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PlaceResponseDto {

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

    public PlaceResponseDto(Place entity) {
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
