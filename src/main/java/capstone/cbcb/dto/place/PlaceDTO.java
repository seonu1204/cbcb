package capstone.cbcb.dto.place;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PlaceDTO {
    private String placeId;
    private String placeName;
    private String address;
    private String contact;
    private String campEnv;
    private String season;
    private String operationDay;
    private String pageUrl;
    private String reservation;
    private String facils;
    private String theme;
    private int placeLike;

    public PlaceDTO(String placeId, String placeName, String address, String contact, String campEnv, String season, String operationDay, String pageUrl, String reservation, String facils, String theme, int placeLike) {
        this.placeId = placeId;
        this.placeName = placeName;
        this.address = address;
        this.contact = contact;
        this.campEnv = campEnv;
        this.season = season;
        this.operationDay = operationDay;
        this.pageUrl = pageUrl;
        this.reservation = reservation;
        this.facils = facils;
        this.theme = theme;
        this.placeLike = placeLike;
    }

}