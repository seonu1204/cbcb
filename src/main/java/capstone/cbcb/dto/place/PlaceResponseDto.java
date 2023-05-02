package capstone.cbcb.dto.place;

import capstone.cbcb.domain.place.Place;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PlaceResponseDto {

    private String place_id;
    private String place_name;
    private String address;
    private String contact;
    private String camp_env;
    private String camp_type;
    private String season;
    private String operation_day;
    private String page_url;
    private String reservation;
    private String facils;
    private int place_like;

    public PlaceResponseDto(Place entity) {
        this.place_id = entity.getPlace_id();
        this.place_name = entity.getPlace_name();
        this.address = entity.getAddress();
        this.contact = entity.getContact();
        this.camp_env = entity.getCamp_env();
        this.camp_type = entity.getCamp_type();
        this.season = entity.getSeason();
        this.operation_day = entity.getOperation_day();
        this.page_url = entity.getPage_url();
        this.reservation = entity.getReservation();
        this.facils = entity.getFacils();
        this.place_like = entity.getPlace_like();
    }
}
