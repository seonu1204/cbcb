package capstone.cbcb.service;

import capstone.cbcb.domain.user.User;
import capstone.cbcb.domain.user.UserRepository;
import capstone.cbcb.dto.user.LoginRequestDTO;
import capstone.cbcb.dto.user.UserRequestDto;
import capstone.cbcb.dto.user.UserResponseDto;
import capstone.cbcb.dto.user.UserUpdateRequestDto;
import capstone.cbcb.infra.jwt.JwtFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final JwtFactory jwtFactory;

    // 회원가입
    public User join(UserRequestDto userRequestDto) {
        Boolean existed = userRepository.existsByEmail(userRequestDto.getEmail());

        if(existed) {
            return null;
        }

        return userRepository.save(User.builder()
                .name(userRequestDto.getName())
                .email(userRequestDto.getEmail())
                .password(userRequestDto.getPassword()) //암호화 필요
                .mycar(userRequestDto.getMycar())
                .phone_number(userRequestDto.getPhone_number())
                .nickname(userRequestDto.getNickname())
                .build());

    }

    // 로그인
    public String login(LoginRequestDTO userRequestDto) throws Exception {

        // Get User Info
        User entity = userRepository.findByEmail(userRequestDto.getEmail())
                .orElseThrow(() -> new Exception("존재하지 않는 유저 정보 입니다."));


        // Check User password
        if(!entity.getPassword().equals(userRequestDto.getPassword())){
            throw new Exception("잘못된 비밀번호 입니다 확인후 로그인해주세요.");
        }

        return jwtFactory.generateAccessToken(
                entity.getUser_id(),
                entity.getEmail(),
                entity.getName(),
                entity.getMycar(),
                entity.getEco_lv(),
                entity.getNickname(),
                entity.getPhone_number()
        );

    }

    private void authenticate(String email, String password) {

    }



    public UserResponseDto userUpdate(String email, UserUpdateRequestDto userUpdateRequestDto) throws Exception {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new Exception("존재하지 않는 유저 정보 입니다."));

        user.userUpdate(userUpdateRequestDto);
        return new UserResponseDto(user);


    }
}

