package capstone.cbcb.controller;

import capstone.cbcb.domain.user.User;
import capstone.cbcb.domain.user.UserRepository;
import capstone.cbcb.dto.user.*;
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

@Slf4j
@RequestMapping("/api/user")
@RequiredArgsConstructor
@RestController
public class UserController {
    private final UserRepository userRepository;

    private final UserService userService;

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

        return ResponseEntity.ok(new LoginResponseDTO(jwtToken));
    }

    @PostMapping("/test")
    private void test(HttpSession httpSession) {
        UserDecodeJWTDTO user = (UserDecodeJWTDTO) httpSession.getAttribute("USER");
        System.out.printf("user");
    }

    @PatchMapping("/profile/{email}")
    public void userUpdate(@PathVariable String email, @RequestBody UserUpdateRequestDto userUpdateRequestDto) throws Exception {
        userService.userUpdate(email, userUpdateRequestDto);
    }

    // 로그아웃
    @PostMapping ("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request, HttpServletResponse response) {
        userService.logout(request, response);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{user_id}")
    public ResponseEntity<?> deleteUser(@PathVariable int user_id) {
        userService.deleteUser(user_id);
        return ResponseEntity.ok().build();
    }
}

