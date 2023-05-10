package capstone.cbcb.domain.coordinate;

import jakarta.persistence.Column;

public class CoordinateId {

    @Column(name = "latitude")
    private String latitude;

    @Column(name = "longitude")
    private String longitude;
}
