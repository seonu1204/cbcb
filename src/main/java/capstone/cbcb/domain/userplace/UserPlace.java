//package capstone.cbcb.domain.userplace;
//
////import capstone.cbcb.dto.place.UserPlaceResponseDto;
//import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import lombok.Builder;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//
//@Getter
//@NoArgsConstructor
//@Entity
//public class UserPlace {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//
//    private String userplace_id;
//    private int userplace_like;
//    private String address;
//    private int report;
//
//    @Builder
//    public UserPlace(String userplace_id, int userplace_like, String address, int report) {
//        this.userplace_id = userplace_id;
//        this.userplace_like = userplace_like;
//        this.address = address;
//        this.report = report;
//    }
//
//    public void update(UserPlaceResponseDto userPlaceResponseDto) {
//        this.userplace_id = userPlaceResponseDto.getUserplace_id();
//        this.userplace_like = userPlaceResponseDto.getUserplace_like();
//        this.address = userPlaceResponseDto.getAddress();
//        this.report = userPlaceResponseDto.getReport();
//    }
//}
