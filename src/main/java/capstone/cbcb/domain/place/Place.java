package capstone.cbcb.domain.place;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
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
    private String theme;
    private int place_like;

}