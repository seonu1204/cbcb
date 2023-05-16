package capstone.cbcb.domain.coordinate;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Entity
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
