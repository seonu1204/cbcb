package capstone.cbcb.dto.place;

import capstone.cbcb.domain.place.Place;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter

@Setter


@NoArgsConstructor
public class PlaceResponseDto {

    private String place_id;
    private String place_name;
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

    // 이미지 경로 관련
    private String[] images = new String[4];

    // response 에는 좌표까지
    private String latitude;
    private String longitude;

    // 상세조회 시 보일 부대시설
    private String amenities;


    public PlaceResponseDto(Place entity) {
        this.place_id = entity.getPlace_id();
        this.place_name = entity.getPlaceName();
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

    // 사진 파일 경로 배열
    public void setImages() {
        String pre = "https://cbcb-bk.s3.ap-northeast-2.amazonaws.com/image/";
        this.images[0] = pre + this.place_id+ "/camp_intro_0.jpg";
        this.images[1] = pre + this.place_id+ "/camp_intro_1.jpg";
        this.images[2] = pre + this.place_id+ "/camp_intro_2.jpg";
        this.images[3] = pre + this.place_id+ "/img_b.jpg";
    }
}
