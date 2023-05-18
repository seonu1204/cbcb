package capstone.cbcb.controller;

import capstone.cbcb.domain.user.User;
import capstone.cbcb.domain.user.UserRepository;
import capstone.cbcb.dto.place.PlaceResponseDto;
import capstone.cbcb.dto.user.*;
import capstone.cbcb.infra.jwt.JwtFactory;
import capstone.cbcb.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Slf4j
@RequestMapping("/api/user")
@RequiredArgsConstructor
@RestController
public class UserController {
    private final UserRepository userRepository;

    private final UserService userService;

    private final JwtFactory jwtFactory;

    // 회원가입
    @PostMapping("/join")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<List<UserResponseDto>> join(@RequestBody UserRequestDto userRequestDto) {
        ResponseEntity<List<UserResponseDto>> result;

        User user = userService.join(userRequestDto);

        if (user == null) {
            result = new ResponseEntity<>(HttpStatus.CONFLICT);
        } else {
            List<UserResponseDto> userResponseDtoList = new ArrayList<>();
            userResponseDtoList.add(new UserResponseDto(user));
            result = ResponseEntity.ok(userResponseDtoList);
        }

        return result;
    }

    // 로그인
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO dto) throws Exception {

        // 로그인 아이디 없음
        // 비밀번호 없음
        if (StringUtils.isEmpty(dto.getEmail())
                || StringUtils.isEmpty(dto.getPassword())) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        // Create Token
        String jwtToken = userService.login(dto);
        jwtFactory.addValidToken(jwtToken);

        Set<String> validTokens = jwtFactory.getValidTokens();
        log.info("Current valid tokens: {}", validTokens);

        return ResponseEntity.ok(new LoginResponseDTO(jwtToken));
    }

    @PostMapping("/test")
    private void test(HttpSession httpSession) {
        UserDecodeJWTDTO user = (UserDecodeJWTDTO) httpSession.getAttribute("USER");
        System.out.printf("user");
    }

    // 사용자 정보 수정
    @PatchMapping("/profile/{email}")
    public void userUpdate(@PathVariable String email, @RequestBody UserUpdateRequestDto userUpdateRequestDto) throws Exception {
        userService.userUpdate(email, userUpdateRequestDto);
    }

    // 로그아웃
    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request, HttpServletResponse response) {
        // 토큰 무효화
        String token = request.getHeader(JwtFactory.HEADER_ACCESS_TOKEN);
        if (token != null) {
            // 토큰 무효화
            jwtFactory.invalidateToken(token, response);

            return ResponseEntity.ok("로그아웃되었습니다.");
        } else {
            return ResponseEntity.badRequest().body("토큰이 존재하지 않습니다.");
        }
    }

    // 회원탈퇴
    @DeleteMapping("/{user_id}")
    public ResponseEntity<?> deleteUser(@PathVariable int user_id) {
        userService.deleteUser(user_id);
        return ResponseEntity.ok().build();
    }

    // 마이페이지
    @GetMapping("/mypage/{email}")
    public ResponseEntity<UserResponseDto> myPage(@PathVariable String email) throws Exception {
        UserResponseDto user = userService.myPage(email);
        return ResponseEntity.ok(user);
    }
}

