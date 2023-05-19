package capstone.cbcb.domain.coordinate.placeCoordinate;


import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Entity

@Setter
@IdClass(CoordinateId.class)
public class Coordinate implements Serializable {

    @Id
    @Column(name = "latitude")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String latitude;

    @Id
    @Column(name = "longitude")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String longitude;

    @Column(name = "place_id")
    private String place_id;
}
