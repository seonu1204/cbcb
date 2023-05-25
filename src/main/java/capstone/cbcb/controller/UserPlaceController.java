package capstone.cbcb.controller;


import capstone.cbcb.dto.place.UserPlaceRequestDto;
import capstone.cbcb.dto.place.UserPlaceResponseDto;
import capstone.cbcb.dto.place.UserPlaceUpdateRequestDTO;
import capstone.cbcb.dto.user.UserDecodeJWTDTO;
import capstone.cbcb.service.UserPlaceService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RequiredArgsConstructor
@RestController
public class UserPlaceController {


    private static final String USER = "USER";
    private final UserPlaceService userPlaceService;


    // 사용자 장소 등록 - 지도로 위치 선택
    @PostMapping("/api/register/coordinate")
    public ResponseEntity<UserPlaceResponseDto> registerByMap(@RequestBody UserPlaceRequestDto userPlaceRequestDto,
                                                         HttpServletRequest request) {
        UserDecodeJWTDTO user =(UserDecodeJWTDTO)request.getSession(true).getAttribute(USER);
        int userId = user.getUser_id();

        UserPlaceResponseDto userPlaceResponseDto = userPlaceService.saveByMap(userPlaceRequestDto, userId);
        return ResponseEntity.ok(userPlaceResponseDto);
    }

    // 사용자 장소 등록 - 주소로 위치 선택
    @PostMapping("/api/register/address")
    public ResponseEntity<UserPlaceResponseDto> registerByAddress(@RequestBody UserPlaceRequestDto userPlaceRequestDto,
                                                                  HttpServletRequest request) {
        UserDecodeJWTDTO user =(UserDecodeJWTDTO)request.getSession(true).getAttribute(USER);
        int userId = user.getUser_id();

        UserPlaceResponseDto userPlaceResponseDto = userPlaceService.saveByAddress(userPlaceRequestDto, userId);
        return ResponseEntity.ok(userPlaceResponseDto);
    }








    // 사용자 장소 수정
    @PatchMapping("/api/user/edit/{userPlaceId}")
    public void userPlaceUpdate(@PathVariable int userPlaceId,
                                @RequestBody UserPlaceUpdateRequestDTO updateDTO) throws Exception{
        userPlaceService.userPlaceUpdate(userPlaceId, updateDTO);
    }


    // 사용자 장소 삭제 -> 마이페이지에서
    @DeleteMapping("/api/user/place/{userPlaceId}")
    public int delete(@PathVariable int userPlaceId) {
        userPlaceService.delete(userPlaceId);
        return userPlaceId;
    }

    // 사용자 장소 신고
    @PatchMapping("/api/place/report/{userPlaceId}")
    public ResponseEntity<UserPlaceResponseDto> report(@PathVariable int userPlaceId) {
        UserPlaceResponseDto userPlaceResponseDto = userPlaceService.report(userPlaceId);
        return ResponseEntity.ok(userPlaceResponseDto);
    }

    // 사용자 장소 좋아요
    @PatchMapping("/api/user/like/{userPlaceId}")
    public ResponseEntity<UserPlaceResponseDto> like(@PathVariable int userPlaceId) {
        UserPlaceResponseDto userPlaceResponseDto = userPlaceService.like(userPlaceId);
        return ResponseEntity.ok(userPlaceResponseDto);
    }


    // 사용자 등록 장소 조회 (메인화면)
    @GetMapping("/api/userplace")
    public ResponseEntity<List<UserPlaceResponseDto>> findAll() {
        List<UserPlaceResponseDto> placeResponseDtoList = userPlaceService.findAll();
        return ResponseEntity.ok(placeResponseDtoList);
    }


    // 사용자(본인) 등록 장소 조회 - (마이페이지용)
    @GetMapping("/api/user/place")
    public ResponseEntity<List<UserPlaceResponseDto>> findMyPlace(HttpServletRequest request) {
        UserDecodeJWTDTO user =(UserDecodeJWTDTO)request.getSession(true).getAttribute(USER);
        int userId = user.getUser_id();

        List<UserPlaceResponseDto> placeResponseDtoList = userPlaceService.findMyPlace(userId);
        return ResponseEntity.ok(placeResponseDtoList);
    }







}
