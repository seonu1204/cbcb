package capstone.cbcb.service;

import capstone.cbcb.domain.place.Place;
import capstone.cbcb.domain.place.PlaceRepository;
import capstone.cbcb.dto.place.PlaceResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor // Repository를 주입하기 위해 사용

@Service
public class PlaceService {

    private final PlaceRepository placeRepository;

    // 테마별 리스트 데이터
    @Transactional(readOnly = true)
    public List<PlaceResponseDto> findByTheme(String theme) {
        List<Place> themeList = placeRepository.findByTheme(theme);
        List<PlaceResponseDto> placeResponseDtoList = new ArrayList<>();

        // dto로 변환
        for( Place place : themeList ) {
            PlaceResponseDto placeResponseDto = new PlaceResponseDto(place);
            placeResponseDtoList.add(placeResponseDto);
        }
        return placeResponseDtoList;
    }


//    // 지역별 리스트 데이터
//    @Transactional(readOnly = true)
//    public List<PlaceResponseDto> findByLoc(String loc) {
//        List<PlaceResponseDto> locList = placeRepository.findByLoc(loc);
//        return locList;
//    }
//
//    // 상세정보
//    @Transactional(readOnly = true)
//    public PlaceResponseDto getDetails(String place_id) {
//        Place place = placeRepository.findById(place_id);
//        return new PlaceResponseDto(place);
//    }


//    // 즐겨찾기
//    @Transactional
//    public PlaceResponseDto bookmark(String place_id) {
//        Place place = placeRepository.bookmark();
//        return new PlaceResponseDto(place);
//    }

}
