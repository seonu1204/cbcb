package capstone.cbcb.service;

import capstone.cbcb.domain.coordinate.userPlaceCoordinate.UserCoordinate;
import capstone.cbcb.domain.coordinate.userPlaceCoordinate.UserCoordinateRepository;
import capstone.cbcb.domain.place.UserPlace;
import capstone.cbcb.domain.place.UserPlaceRepository;
import capstone.cbcb.dto.place.UserPlaceRequestDto;
import capstone.cbcb.dto.place.UserPlaceResponseDto;
import capstone.cbcb.dto.place.UserPlaceUpdateRequestDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor // Repository를 주입하기 위해 사용
@Service
public class UserPlaceService {

    private final UserPlaceRepository userPlaceRepository;
    private final UserCoordinateRepository userCoordinateRepository;
    private final UserService userService;


    // 사용자 장소 등록
    @Transactional
    public UserPlaceResponseDto save(UserPlaceRequestDto userPlaceRequestDto,
                                     int userId) {

        String latitude = userPlaceRequestDto.getLatitude();
        String longitude = userPlaceRequestDto.getLongitude();

        UserPlace existed = userPlaceRepository.existsByCoordinate(latitude, longitude);

        // 이미 존재한다면 예외 처리
        if(existed != null) {
            return new UserPlaceResponseDto();
        }

        // 존재하지 않을 경우 장소 등록
        userPlaceRequestDto.setUser_id(userId);  // 사용자 id 넣어줌
        UserPlace userPlace = userPlaceRepository.save(userPlaceRequestDto.toEntity());

        // 좌표 등록
        registerUserCoordinate(userPlace);

        return new UserPlaceResponseDto(userPlace);
    }


    // 사용자 장소 수정
    @Transactional
    public void userPlaceUpdate(int userPlaceId, UserPlaceUpdateRequestDTO updateDTO) throws Exception {
        try {
            UserPlace userPlace = userPlaceRepository.findByUserPlaceId(userPlaceId);
            userPlace.update(updateDTO);
        } catch (Exception e) {
            throw new Exception("존재하지 않는 장소 정보입니다.");
        }
    }


    // 사용자 장소 삭제
    @Transactional
    public void delete(int userPlaceId) {
        // 좌표값 먼저 지워줌
        userCoordinateRepository.delete(userPlaceId);

        UserPlace userPlace = userPlaceRepository.findByUserPlaceId(userPlaceId);
        userPlaceRepository.delete(userPlace);
    }

    // 사용자 장소 신고
    @Transactional
    public UserPlaceResponseDto report(int userPlaceId) {
        UserPlace userPlace = userPlaceRepository.findByUserPlaceId(userPlaceId);
        userPlace.setReport(userPlace.getReport()+1);

        userPlaceRepository.save(userPlace);
        return new UserPlaceResponseDto(userPlace);
    }


    // 사용자 장소 좋아요
    @Transactional
    public UserPlaceResponseDto like(int userPlaceId) {
        UserPlace userPlace = userPlaceRepository.findByUserPlaceId(userPlaceId);
        userPlace.setUserPlaceLike(userPlace.getUserPlaceLike()+1);

        userPlaceRepository.save(userPlace);
        return new UserPlaceResponseDto(userPlace);
    }


    // id로 사용자 장소 찾기 (사용자가 등록한 장소 목록) - (마이페이지용)
    @Transactional(readOnly = true)
    public List<UserPlaceResponseDto> findMyPlace(int user_id){
        List<UserPlace> placeList = userPlaceRepository.findByIdList(user_id);
        List<UserPlaceResponseDto> userPlaceResponseDtoList = new ArrayList<>();

        // dto 로 변환
        for( UserPlace place : placeList ) {
            UserPlaceResponseDto userPlaceResponseDto = new UserPlaceResponseDto(place);
            userPlaceResponseDtoList.add(userPlaceResponseDto);
        }
        return userPlaceResponseDtoList;
    }


    // 사용자 장소 목록 전체 조회
    @Transactional(readOnly = true)
    public List<UserPlaceResponseDto> findAll() {
        List<UserPlace> placeList = userPlaceRepository.findAll();
        List<UserPlaceResponseDto> userPlaceResponseDtoList = new ArrayList<>();

        // dto 로 변환
        for( UserPlace place : placeList ) {
            UserPlaceResponseDto userPlaceResponseDto = new UserPlaceResponseDto(place);
            userPlaceResponseDtoList.add(userPlaceResponseDto);
        }
        return userPlaceResponseDtoList;
    }


    // 사용자 장소 좌표 db 등록
    public void registerUserCoordinate( UserPlace userPlace) {

        if(userPlaceRepository.findByUserPlaceId(userPlace.getUserPlaceId()) != null){
            // 좌표 테이블에 좌표 등록
            UserCoordinate userCoordinate = new UserCoordinate();
            userCoordinate.setUserplace_id(userPlace.getUserPlaceId());
            userCoordinate.setLatitude(userPlace.getLatitude());
            userCoordinate.setLongitude(userPlace.getLongitude());
            userCoordinateRepository.save(userCoordinate);
        }
    }
}
