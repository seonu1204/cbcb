package capstone.cbcb.controller;

import capstone.cbcb.domain.user.User;
import capstone.cbcb.domain.user.UserRepository;
import capstone.cbcb.dto.user.*;
import capstone.cbcb.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@CrossOrigin
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




        User user = userService.join(userRequestDto);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        List<UserResponseDto> userResponseDtoList = new ArrayList<>();
        userResponseDtoList.add(new UserResponseDto(user));

        return ResponseEntity.ok(userResponseDtoList);

    }

    // 로그인
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO dto) throws Exception {

        // 로그인 아이디 없음
        // 비밀번호 없음
        if(StringUtils.isEmpty(dto.getEmail())
        || StringUtils.isEmpty(dto.getPassword())){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        // Create Token
        String jwtToken = userService.login(dto);

        return ResponseEntity.ok(new LoginResponseDTO(jwtToken));
    }
    @PostMapping("/test")
    private void test(HttpSession httpSession){
        UserDecodeJWTDTO user = (UserDecodeJWTDTO) httpSession.getAttribute("USER");

                
        System.out.printf("user");
    }

//    @PatchMapping("/profile")
//    public ResponseEntity<List<UserResponseDto>> userUpdate(@PathVariable String email, @RequestBody UserUpdateRequestDto userUpdateRequestDto) throws Exception {
//        User user = userService.userUpdate(email, userUpdateRequestDto);
//
//        List<UserResponseDto> userResponseDtoList = new ArrayList<>();
//        userResponseDtoList.add(new UserResponseDto(user));
//
//        return ResponseEntity.ok(userResponseDtoList);
//    }
}

