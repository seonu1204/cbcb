package capstone.cbcb.domain.coordinate;


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
public class Coordinate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String latitude;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String longitude;

    // 연관관계 mapping 해줘야 하는데 그럼 place, userplace 객체 필드 두 개가 필요함..
    // 그냥 주입 안 하는 걸로
    private String place_id;
}
