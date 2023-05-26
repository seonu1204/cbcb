package capstone.cbcb.service;


import capstone.cbcb.domain.bookmark.Bookmark;
import capstone.cbcb.domain.bookmark.BookmarkRepository;
import capstone.cbcb.domain.coordinate.placeCoordinate.Coordinate;
import capstone.cbcb.domain.coordinate.placeCoordinate.CoordinateRepository;
import capstone.cbcb.domain.facility.FacilityRepository;
import capstone.cbcb.domain.facility.QFacility;
import capstone.cbcb.domain.place.Place;
import capstone.cbcb.domain.place.PlaceRepository;
import capstone.cbcb.domain.place.QPlace;
import capstone.cbcb.dto.place.PlaceResponseDto;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor // Repository 를 주입하기 위해 사용
@Service
public class PlaceService {

    private final PlaceRepository placeRepository;
    private final BookmarkRepository bookmarkRepository;

//    @Autowired
    private final CoordinateRepository coordinateRepository;

    private final FacilityRepository facilityRepository;

    private final EntityManager entityManager;


    // 추천지 리스트 데이터
    @Transactional(readOnly = true)
    public List<PlaceResponseDto> recommend() {
        List<Place> placeList = placeRepository.findAll();
        List<Place> recommendList = new ArrayList<>();
        List<PlaceResponseDto> placeResponseDtoList = new ArrayList<>();

        int idx;

        Random random = new Random();
        for(int i = 0; i < 5; i++) {
            idx = random.nextInt(placeList.size())+1;
            recommendList.add(placeList.get(idx));
        }

        // dto 로 변환
        for( Place place : recommendList ) {
            PlaceResponseDto placeResponseDto = new PlaceResponseDto(place);
            placeResponseDto.setImages();

            Coordinate coordinate = coordinateRepository.findByPlaceId(placeResponseDto.getPlace_id());

            if(coordinate != null) {
                placeResponseDto.setLatitude(coordinate.getLatitude());
                placeResponseDto.setLongitude(coordinate.getLongitude());
            }
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
            placeResponseDto.setImages();

            Coordinate coordinate = coordinateRepository.findByPlaceId(placeResponseDto.getPlace_id());

            if(coordinate != null) {
                placeResponseDto.setLatitude(coordinate.getLatitude());
                placeResponseDto.setLongitude(coordinate.getLongitude());
            }
            placeResponseDtoList.add(placeResponseDto);
        }
        return placeResponseDtoList;
    }



    // 지역별 리스트 데이터
    @Transactional(readOnly = true)
    public List<PlaceResponseDto> findByAddress(String loc) {

        List<Place> locList = new ArrayList<>();
        locList = (loc.equals("전국"))
                ? placeRepository.findAll()
                : placeRepository.findByAddressContains(loc);
        List<PlaceResponseDto> placeResponseDtoList = new ArrayList<>();


        // dto 로 변환
        for( Place place : locList ) {
            PlaceResponseDto placeResponseDto = new PlaceResponseDto(place);
            placeResponseDto.setImages();

            Coordinate coordinate = coordinateRepository.findByPlaceId(placeResponseDto.getPlace_id());
            if(coordinate != null) {
                placeResponseDto.setLatitude(coordinate.getLatitude());
                placeResponseDto.setLongitude(coordinate.getLongitude());
            }
            placeResponseDtoList.add(placeResponseDto);
        }
        return placeResponseDtoList;
    }


    // 지역&테마별 리스트 데이터
    @Transactional(readOnly = true)
    public List<PlaceResponseDto> findByAddressAndTheme(String loc, String theme) {

        List<Place> placeList = new ArrayList<>();
        placeList = (loc.equals("전국"))
                ? placeRepository.findByThemeContains("theme")
                : placeRepository.findByAddressContainsAndThemeContains(loc, theme);
        List<PlaceResponseDto> placeResponseDtoList = new ArrayList<>();

        // dto 로 변환
        for( Place place : placeList ) {
            PlaceResponseDto placeResponseDto = new PlaceResponseDto(place);
            placeResponseDto.setImages();

            Coordinate coordinate = coordinateRepository.findByPlaceId(placeResponseDto.getPlace_id());
            if(coordinate != null) {
                placeResponseDto.setLatitude(coordinate.getLatitude());
                placeResponseDto.setLongitude(coordinate.getLongitude());
            }

            placeResponseDtoList.add(placeResponseDto);
        }
        return placeResponseDtoList;

    }


    // 검색 (장소 이름 기준)
    @Transactional(readOnly = true)
    public List<PlaceResponseDto> findByName(String keyword) {

        List<Place> placeList = placeRepository.findByNameContainsOrAddressContains(keyword);
        List<PlaceResponseDto> placeResponseDtoList = new ArrayList<>();

        // dto 로 변환
        for( Place place : placeList ) {
            PlaceResponseDto placeResponseDto = new PlaceResponseDto(place);
            placeResponseDto.setImages();

            Coordinate coordinate = coordinateRepository.findByPlaceId(placeResponseDto.getPlace_id());
            if(coordinate != null) {
                placeResponseDto.setLatitude(coordinate.getLatitude());
                placeResponseDto.setLongitude(coordinate.getLongitude());
            }

            placeResponseDtoList.add(placeResponseDto);
        }
        return placeResponseDtoList;

    }

    // 검색 - 필터링
    @Transactional(readOnly = true)
    public List<PlaceResponseDto> searchByFilters(String keyword,
                                                  List<String> themes, List<String> facils) {

        QPlace qPlace = QPlace.place;
        QFacility qFacility = QFacility.facility;

        // Keyword 조건
        String keywordPattern = "%" + keyword + "%";
        BooleanExpression keywordExpression = qPlace.placeName.likeIgnoreCase(keywordPattern)
                .or(qPlace.address.likeIgnoreCase(keywordPattern));

        // Theme 조건
        BooleanExpression themeExpression = themes.stream()
                .map(theme -> qPlace.theme.like("%" + theme + "%"))
                .reduce(BooleanExpression::and)
                .orElse(null);

        BooleanExpression facilityExpression = facils.stream()
                .map(facility -> qFacility.place_id_f.eq(qPlace.place_id).and(qFacility.amenities.like("%" + facility + "%")))
                .reduce(BooleanExpression::and)
                .orElse(null);


        JPAQuery<Place> query = new JPAQuery<>(entityManager);
        List<Place> result = query.select(qPlace)
                .from(qPlace)
                .leftJoin(qFacility).on(qFacility.place_id_f.eq(qPlace.place_id))
                .where(keywordExpression, themeExpression, facilityExpression)
                .fetch();



        List<PlaceResponseDto> placeResponseDtoList = new ArrayList<>();

        // dto로 변환
        for( Place place : result ) {
            PlaceResponseDto placeResponseDto = new PlaceResponseDto(place);
            placeResponseDto.setImages();

            Coordinate coordinate = coordinateRepository.findByPlaceId(placeResponseDto.getPlace_id());
            if(coordinate != null) {
                placeResponseDto.setLatitude(coordinate.getLatitude());
                placeResponseDto.setLongitude(coordinate.getLongitude());
            }

            placeResponseDtoList.add(placeResponseDto);
        }
        return placeResponseDtoList;
    }


    // 장소 1개 검색(상세 정보)
    @Transactional(readOnly = true)
    public PlaceResponseDto findById(String id) {
        Place place = placeRepository.findById(id);
        PlaceResponseDto placeResponseDto = new PlaceResponseDto(place);
        placeResponseDto.setImages();

        // 부대 시설 추가
        String amenities = facilityRepository.findByPlaceIdF(place.getPlace_id());
        placeResponseDto.setAmenities(amenities);

        Coordinate coordinate = coordinateRepository.findByPlaceId(placeResponseDto.getPlace_id());
        if(coordinate != null) {
            placeResponseDto.setLatitude(coordinate.getLatitude());
            placeResponseDto.setLongitude(coordinate.getLongitude());
        }

        return placeResponseDto;
    }



    // 장소 즐겨찾기
    @Transactional
    public PlaceResponseDto bookmark(int user_id, String place_id) {

        Bookmark bookmark = new Bookmark(user_id, place_id);
        bookmarkRepository.save(bookmark);

        Place byId = placeRepository.findById(place_id);

        return new PlaceResponseDto(byId);
    }


    // 장소 즐겨찾기 해제
    @Transactional
    public void bookmarkDelete(String place_id) {
        Bookmark place = bookmarkRepository.findByPlaceId(place_id);
        bookmarkRepository.delete(place);
    }


    // 장소 좋아요
    @Transactional
    public PlaceResponseDto like(String placeId) {
        Place place = placeRepository.findById(placeId);
        place.setPlaceLike(place.getPlace_like()+1);

        placeRepository.save(place);
        return new PlaceResponseDto(place);
    }


    // 검색 기능 (챗봇)
    @Transactional
    public List<PlaceResponseDto> searchChatBot(String gpe, String city, String season, String theme) {

        List<Place> placeList = placeRepository.searchChatbot(gpe, city, season, theme);
        List<PlaceResponseDto> placeResponseDtoList = new ArrayList<>();

        // dto로 변환
        for( Place place : placeList ) {
            PlaceResponseDto placeResponseDto = new PlaceResponseDto(place);
            placeResponseDto.setImages();

            Coordinate coordinate = coordinateRepository.findByPlaceId(placeResponseDto.getPlace_id());

            if(coordinate != null) {
                placeResponseDto.setLatitude(coordinate.getLatitude());
                placeResponseDto.setLongitude(coordinate.getLongitude());
            }

            placeResponseDtoList.add(placeResponseDto);
        }
        return placeResponseDtoList;

    }

    // 사용자가 즐겨찾기한 장소 리스트 조회 - (마이페이지용)
    @Transactional(readOnly = true)
    public List<PlaceResponseDto> findBookmarkList(int userId) {

        List<String> list = bookmarkRepository.findByUserId(userId);
        List<Place> placeList = new ArrayList<>();

        // id로 Place 리스트 변환
        for (String id : list) {
            Place place = placeRepository.findById(id);
            placeList.add(place);
        }

        List<PlaceResponseDto> placeResponseDtoList = new ArrayList<>();

        // dto로 변환
        for( Place place : placeList ) {
            PlaceResponseDto placeResponseDto = new PlaceResponseDto(place);
            placeResponseDto.setImages();

            Coordinate coordinate = coordinateRepository.findByPlaceId(placeResponseDto.getPlace_id());
            if(coordinate != null) {
                placeResponseDto.setLatitude(coordinate.getLatitude());
                placeResponseDto.setLongitude(coordinate.getLongitude());
            }

            placeResponseDtoList.add(placeResponseDto);
        }
        return placeResponseDtoList;
    }


}
