package capstone.cbcb.domain.place;

import jakarta.persistence.*;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class Place {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    private int place_like;


}



