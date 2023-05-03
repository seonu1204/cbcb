package capstone.cbcb.controller;


import capstone.cbcb.domain.place.Place;
import capstone.cbcb.dto.place.PlaceResponseDto;
import capstone.cbcb.service.PlaceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@CrossOrigin
@RequiredArgsConstructor
@RestController
public class PlaceController {

    private final PlaceService placeService;

    // 테마별 리스트 조회
    @GetMapping("/api/place/{theme}")
    public ResponseEntity<List<PlaceResponseDto>> findByTheme(@PathVariable String theme){
        List<PlaceResponseDto> placeResponseDtoList = placeService.findByTheme(theme);
        return ResponseEntity.ok(placeResponseDtoList);
    }


//    // 지역별 리스트 조회
//    @GetMapping("/api/place/{loc}")
//    public ResponseEntity<List<PlaceResponseDto>> findByLoc(@PathVariable String loc){
//        List<PlaceResponseDto> placeResponseDtoList = placeService.findByLoc(loc);
//        return ResponseEntity.ok(placeResponseDtoList);
//    }
//
//    // 장소 상세정보 조회
//    @GetMapping("/api/place/{place_id}")
//    public PlaceResponseDto details(@PathVariable String place_id) {
//        return placeService.getDetails(place_id);
//    }
//
//    // 장소 즐겨찾기
//    @PostMapping("/api/place/{place_id}")
//    public PlaceResponseDto bookmark(@PathVariable String place_id) {
//        Place place = placeService.findById(place_id);
//        return placeService.bookmark(place_id);
//
//    }


    // 사용자 장소 등록

}
