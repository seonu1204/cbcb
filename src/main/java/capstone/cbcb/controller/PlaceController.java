package capstone.cbcb.controller;

import capstone.cbcb.domain.place.UserPlace;
import capstone.cbcb.dto.place.PlaceResponseDto;
import capstone.cbcb.dto.place.UserPlaceResponseDto;
import capstone.cbcb.dto.user.UserDecodeJWTDTO;
import capstone.cbcb.service.PlaceService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.Banner;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@Slf4j
@RequiredArgsConstructor
@RestController
public class PlaceController {

    private final PlaceService placeService;


    // 추천지 목록 조회
    @GetMapping("/api/place/recommend")
    public ResponseEntity<List<PlaceResponseDto>> recommend(Model model) {
        List<PlaceResponseDto> placeResponseDtoList = placeService.recommend();
        return ResponseEntity.ok(placeResponseDtoList);
    }

    // 테마별 리스트 조회
    @GetMapping("/api/place/theme/{theme}")
    public ResponseEntity<List<PlaceResponseDto>> findByTheme(@PathVariable String theme){
        List<PlaceResponseDto> placeResponseDtoList = placeService.findByTheme(theme);
        return ResponseEntity.ok(placeResponseDtoList);
    }

    // 지역별 리스트 조회
    @GetMapping("/api/place/location/{loc}")
    public ResponseEntity<List<PlaceResponseDto>> findByAddress(@PathVariable String loc){
        List<PlaceResponseDto> placeResponseDtoList = placeService.findByAddress(loc);
        return ResponseEntity.ok(placeResponseDtoList);
    }

    // 지역&테마별 리스트 조회
    @GetMapping("/api/place/{loc}/{theme}")
    public ResponseEntity<List<PlaceResponseDto>> findByLocAndTheme(@PathVariable String loc, @PathVariable String theme){
        List<PlaceResponseDto> placeResponseDtoList = placeService.findByAddressAndTheme(loc, theme);
        return ResponseEntity.ok(placeResponseDtoList);
    }

    // 검색 목록
    @GetMapping("/api/place/search/{keyword}")
    public ResponseEntity<List<PlaceResponseDto>> search(@PathVariable String keyword){
        List<PlaceResponseDto> placeResponseDtoList = placeService.findByName(keyword);
        return ResponseEntity.ok(placeResponseDtoList);
    }



    // 검색 목록 - 필터링
//    @GetMapping("/api/place/search/filter/{keyword}")
//    public ResponseEntity<List<PlaceResponseDto>> searchFiltered(@RequestBody )




    // 장소 상세정보 조회
    @GetMapping("/api/place/detail/{place_id}")
    public ResponseEntity<PlaceResponseDto> findById(@PathVariable String place_id){
        PlaceResponseDto place = placeService.findById(place_id);

//        String[] images = new String[4];

//        setImages(images, place_id);
//        model.addAttribute("place", place);
//        model.addAttribute("images", images);

        return ResponseEntity.ok(place);
    }


    // 장소 즐겨찾기
    @PostMapping("/api/place/{place_id}")
    public ResponseEntity<PlaceResponseDto> bookmark( HttpServletRequest request,
            @PathVariable String place_id) {

        UserDecodeJWTDTO user = (UserDecodeJWTDTO)request.getAttribute("USER");
        PlaceResponseDto bookmark = placeService.bookmark(place_id, user);
        return ResponseEntity.ok(bookmark);
    }

    // 장소 좋아요
    @PatchMapping("/api/place/like/{placeId}")
    public ResponseEntity<PlaceResponseDto> like(@PathVariable String placeId) {
        PlaceResponseDto placeResponseDto = placeService.like(placeId);
        return ResponseEntity.ok(placeResponseDto);
    }

//    // 검색 기능 (챗봇)
//    @GetMapping("/api/chatbot/place")
//    public ResponseEntity<List<PlaceResponseDto>> searchChatBot(@RequestBody Map <String, String> keyword) {
//        List<PlaceResponseDto> placeResponseDto = placeService.searchChatBot(keyword);
//        return ResponseEntity.ok(placeResponseDto);
//    }


//    // 사진 파일
//    public void setImages(String[] images, String place_id) {
//        images[0] = "/resources/image/" + place_id+ "/camp_intro_0.jpg";
//        images[1] = "/resources/image/" + place_id+ "/camp_intro_1.jpg";
//        images[2] = "/resources/image/" + place_id+ "/camp_intro_2.jpg";
//        images[3] = "/resources/image/" + place_id+ "/img_b.jpg";
//    }


}


