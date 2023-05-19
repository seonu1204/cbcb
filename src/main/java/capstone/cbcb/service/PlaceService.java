package capstone.cbcb.service;

import capstone.cbcb.domain.bookmark.Bookmark;
import capstone.cbcb.domain.bookmark.BookmarkRepository;
import capstone.cbcb.domain.place.Place;
import capstone.cbcb.domain.place.PlaceRepository;
import capstone.cbcb.domain.place.UserPlace;
import capstone.cbcb.dto.place.PlaceResponseDto;
import capstone.cbcb.dto.place.UserPlaceResponseDto;
import capstone.cbcb.dto.user.UserDecodeJWTDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Slf4j
@RequiredArgsConstructor // Repository 를 주입하기 위해 사용
@Service
public class PlaceService {

    private final PlaceRepository placeRepository;
    private final BookmarkRepository bookmarkRepository;


    // 추천지 리스트 데이터
    @Transactional(readOnly = true)
    public List<PlaceResponseDto> recommend() {
        List<Place> placeList = placeRepository.findAll();
        List<Place> recommendList = new ArrayList<>();
        List<PlaceResponseDto> placeResponseDtoList = new ArrayList<>();

        int idx = 0;

        Random random = new Random();
        for(int i = 0; i < 5; i++) {
            idx = random.nextInt(placeList.size())+1;
            recommendList.add(placeList.get(idx));
        }

        // dto 로 변환
        for( Place place : recommendList ) {
            PlaceResponseDto placeResponseDto = new PlaceResponseDto(place);
            placeResponseDtoList.add(placeResponseDto);
        }
        return placeResponseDtoList;
    }


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

    // 장소 1개 검색(상세  정보)
    @Transactional(readOnly = true)
    public PlaceResponseDto findById(String id) {
        Place place = placeRepository.findById(id);
        return new PlaceResponseDto(place);
    }

    // 장소 즐겨찾기
    @Transactional
    public PlaceResponseDto bookmark(String place_id, UserDecodeJWTDTO userDecodeJWTDTO) {

        Bookmark bookmark = new Bookmark(userDecodeJWTDTO.getUser_id(), place_id);
        bookmarkRepository.save(bookmark);

        Place byId = placeRepository.findById(place_id);

        return new PlaceResponseDto(byId);
    }

    // 장소 좋아요
    @Transactional
    public PlaceResponseDto like(String placeId) {
        Place place = placeRepository.findById(placeId);
        place.setPlaceLike(place.getPlace_like()+1);

        placeRepository.save(place);
        return new PlaceResponseDto(place);
    }

//    @Transactional
//    public List<PlaceResponseDto> searchChatBot(Map<String, String> keyword) {
//
//        List<PlaceResponseDto> placeResponseDtoList = new ArrayList<>();
//        List<Place> list = new ArrayList<>();
//        int len = keyword.size();
//
//        for( String address : keyword.keySet() ) {
//            if (address == "GPE" || address == "CITY") {
//
//            }
//        }
//
//
//
//
//        if(len == 4) {
//            list = placeRepository.findBy4Keyword(keyword);
//        } else if(len == 3) {
//            list = placeRepository.findBy3Keyword(keyword);
//        } else if (len == 2) {
//            list = placeRepository.findBy2Keyword(keyword);
//        } else {
//            list = placeRepository.findBy1Keyword(keyword);
//        }
//
//        // dto로 변환
//        for( Place place : list ) {
//            PlaceResponseDto placeResponseDto = new PlaceResponseDto(place);
//            placeResponseDtoList.add(placeResponseDto);
//        }
//        return placeResponseDtoList;
//
//    }





}
