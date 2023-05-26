package capstone.cbcb.domain.coordinate.userPlaceCoordinate;

import jakarta.persistence.Column;

public class UserCoordinateId {

    @Column(name = "latitude")
    private String latitude;

    @Column(name = "longitude")
    private String longitude;

}