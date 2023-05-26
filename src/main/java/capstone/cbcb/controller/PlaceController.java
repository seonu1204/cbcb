package capstone.cbcb.controller;

import capstone.cbcb.domain.place.Place;
import capstone.cbcb.dto.place.PlaceResponseDto;
import capstone.cbcb.dto.user.UserDecodeJWTDTO;
import capstone.cbcb.service.PlaceService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RequiredArgsConstructor
@RestController
public class PlaceController {

    private static final String USER = "USER";
    private final PlaceService placeService;


    // 추천지 리스트 조회
    @GetMapping("/api/place/recommend")
    public ResponseEntity<List<PlaceResponseDto>> recommend(Model model) {
        List<PlaceResponseDto> placeResponseDtoList = placeService.recommend();
        return ResponseEntity.ok(placeResponseDtoList);
    }

    // 테마별 리스트 조회
    @GetMapping("/api/place/theme/{theme}")
    public ResponseEntity<List<PlaceResponseDto>> findByTheme(@PathVariable String theme) {

        List<PlaceResponseDto> placeResponseDtoList = placeService.findByTheme(theme);
        return ResponseEntity.ok(placeResponseDtoList);
    }

    // 지역별 리스트 조회
    @GetMapping("/api/place/location/{loc}")
    public ResponseEntity<List<PlaceResponseDto>> findByAddress(@PathVariable String loc) {
        List<PlaceResponseDto> placeResponseDtoList = placeService.findByAddress(loc);
        return ResponseEntity.ok(placeResponseDtoList);
    }

    // 지역&테마별 리스트 조회
    @GetMapping("/api/place/{loc}/{theme}")
    public ResponseEntity<List<PlaceResponseDto>> findByLocAndTheme(@PathVariable String loc, @PathVariable String theme) {
        List<PlaceResponseDto> placeResponseDtoList = placeService.findByAddressAndTheme(loc, theme);
        return ResponseEntity.ok(placeResponseDtoList);
    }

    // 검색 목록
    @GetMapping("/api/place/search/{keyword}")
    public ResponseEntity<List<PlaceResponseDto>> search(@PathVariable String keyword) {
        List<PlaceResponseDto> placeResponseDtoList = placeService.findByName(keyword);
        return ResponseEntity.ok(placeResponseDtoList);
    }


    // 검색 목록 - 필터링
    @GetMapping("/api/place/search/filter/{keyword}")
    public ResponseEntity<List<PlaceResponseDto>> searchByFilters(
            @PathVariable String keyword,
            @RequestParam(value = "themes", required = false) List<String> themes,
            @RequestParam(value = "facils", required = false) List<String> facils) {
        List<PlaceResponseDto> placeResponseDtoList = placeService.searchByFilters(keyword, themes, facils);
        return ResponseEntity.ok(placeResponseDtoList);
    }


    // 장소 상세정보 조회
    @GetMapping("/api/place/detail/{place_id}")
    public ResponseEntity<PlaceResponseDto> findById(@PathVariable String place_id) {
        PlaceResponseDto place = placeService.findById(place_id);
        return ResponseEntity.ok(place);
    }


    // 장소 즐겨찾기 등록
    @PostMapping("/api/bookmark/{place_id}")
    public ResponseEntity<PlaceResponseDto> bookmark(HttpServletRequest request,
                                                     @PathVariable String place_id) {

        UserDecodeJWTDTO user =(UserDecodeJWTDTO)request.getSession(true).getAttribute(USER);
        int userId = user.getUser_id();

        PlaceResponseDto bookmark = placeService.bookmark(userId, place_id);
        return ResponseEntity.ok(bookmark);
    }


    // 장소 즐겨찾기 해제
    @DeleteMapping("/api/bookmark/{place_id}")
    public void bookmarkDelete(@PathVariable String place_id) {
        placeService.bookmarkDelete(place_id);
    }


    // 장소 좋아요
    @PatchMapping("/api/place/like/{placeId}")
    public ResponseEntity<PlaceResponseDto> like(@PathVariable String placeId) {
        PlaceResponseDto placeResponseDto = placeService.like(placeId);
        return ResponseEntity.ok(placeResponseDto);
    }


    // 검색 기능 (챗봇)
    @GetMapping("/api/chatbot/place")
    public ResponseEntity<List<PlaceResponseDto>> searchChatBot(
            @RequestParam(value = "gpe", required = false) String gpe,
            @RequestParam(value = "city", required = false) String city,
            @RequestParam(value = "season", required = false) String season,
            @RequestParam(value = "theme", required = false) String theme) {
        List<PlaceResponseDto> placeList = placeService.searchChatBot(gpe, city, season, theme);
        return ResponseEntity.ok(placeList);
    }


    // 사용자가 즐겨찾기한 장소 리스트 조회 - (마이페이지용)
    @GetMapping("/api/user/place/bookmark")
    public ResponseEntity<List<PlaceResponseDto>> getBookmarkList(HttpServletRequest request) {
        UserDecodeJWTDTO user =(UserDecodeJWTDTO)request.getSession(true).getAttribute(USER);
        int userId = user.getUser_id();

        List<PlaceResponseDto> placeResponseDto = placeService.findBookmarkList(userId);
        return ResponseEntity.ok(placeResponseDto);
    }

}


