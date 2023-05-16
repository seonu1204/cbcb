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
@RequiredArgsConstructor // Repository 를 주입하기 위해 사용
@Service
public class PlaceService {

    private final PlaceRepository placeRepository;

    // 테마별 리스트 데이터
    @Transactional(readOnly = true)
    public List<PlaceResponseDto> findByTheme(String theme) {
        List<Place> themeList = placeRepository.findByThemeContains(theme);
        List<PlaceResponseDto> placeResponseDtoList = new ArrayList<>();

        // dto 로 변환
        for( Place place : themeList ) {
            PlaceResponseDto placeResponseDto = new PlaceResponseDto(place);
            placeResponseDtoList.add(placeResponseDto);
        }
        return placeResponseDtoList;
    }


    // 지역별 리스트 데이터
    @Transactional(readOnly = true)
    public List<PlaceResponseDto> findByAddress(String loc) {

        List<Place> locList = placeRepository.findByAddressContains(loc);
        List<PlaceResponseDto> placeResponseDtoList = new ArrayList<>();

        // dto로 변환
        for( Place place : locList ) {
            PlaceResponseDto placeResponseDto = new PlaceResponseDto(place);
            placeResponseDtoList.add(placeResponseDto);
        }
        return placeResponseDtoList;

    }


    // 지역&테마별 리스트 데이터
    @Transactional(readOnly = true)
    public List<PlaceResponseDto> findByAddressAndTheme(String loc, String theme) {

        List<Place> placeList = placeRepository.findByAddressContainsAndThemeContains(loc, theme);
        List<PlaceResponseDto> placeResponseDtoList = new ArrayList<>();

        // dto로 변환
        for( Place place : placeList ) {
            PlaceResponseDto placeResponseDto = new PlaceResponseDto(place);
            placeResponseDtoList.add(placeResponseDto);
        }
        return placeResponseDtoList;

    }


    // 검색 (장소 이름 기준)
    @Transactional(readOnly = true)
    public List<PlaceResponseDto> findByName(String keyword) {

        List<Place> placeList = placeRepository.findByNameContainsOrAddressContains(keyword);
        List<PlaceResponseDto> placeResponseDtoList = new ArrayList<>();

        // dto로 변환
        for( Place place : placeList ) {
            PlaceResponseDto placeResponseDto = new PlaceResponseDto(place);
            placeResponseDtoList.add(placeResponseDto);
        }
        return placeResponseDtoList;

    }

    // 장소 1개 검색(상세 정보)
    @Transactional(readOnly = true)
    public PlaceResponseDto findById(String id) {
        Place place = placeRepository.findById(id);
        return new PlaceResponseDto(place);
    }

//    // 장소 즐겨찾기



}
