package capstone.cbcb.controller;

import capstone.cbcb.dto.place.PlaceResponseDto;
import capstone.cbcb.service.PlaceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
public class PlaceController {

    private final PlaceService placeService;

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

    // 장소 상세정보 조회
    @GetMapping("/api/place/detail/{place_id}")
    public ResponseEntity<PlaceResponseDto> findById(@PathVariable String place_id){
        PlaceResponseDto place = placeService.findById(place_id);
        return ResponseEntity.ok(place);
    }

}
