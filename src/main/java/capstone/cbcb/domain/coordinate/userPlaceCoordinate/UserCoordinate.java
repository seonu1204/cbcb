package capstone.cbcb.domain.coordinate.userPlaceCoordinate;


import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Entity

@Setter
@IdClass(UserCoordinateId.class)
public class UserCoordinate implements Serializable {

    @Id
    @Column(name = "latitude")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String latitude;

    @Id
    @Column(name = "longitude")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String longitude;

    @Column(name = "userplace_id")
    private int userplace_id;
}
