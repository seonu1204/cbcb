//package capstone.cbcb.service;
//
//
//import capstone.cbcb.domain.place.UserPlace;
//import capstone.cbcb.domain.place.UserPlaceRepository;
//import capstone.cbcb.dto.place.UserPlaceResponseDto;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.List;
//
//
//@Slf4j
//@RequiredArgsConstructor // Repository를 주입하기 위해 사용
//@Service
//public class UserPlaceService {
//
//    private final UserPlaceRepository userPlaceRepository;
//
//    // id로 사용자 장소 찾기
//    public List<UserPlaceResponseDto> findById(String id){
//        List<UserPlace> placeList = userPlaceRepository.findById(id);
//        List<UserPlaceResponseDto> userPlaceResponseDtoList = new ArrayList<>();
//
//        // dto 로 변환
//        for( UserPlace place : placeList ) {
//            UserPlaceResponseDto userPlaceResponseDto = new UserPlaceResponseDto(place);
//            userPlaceResponseDtoList.add(userPlaceResponseDto);
//        }
//        return userPlaceResponseDtoList;
//    }
//
//    // 사용자 장소 등록
//
//
//    // 사용자 장소 수정
//
//
//    // 사용자 장소 신고
//
//
//
//
//}
