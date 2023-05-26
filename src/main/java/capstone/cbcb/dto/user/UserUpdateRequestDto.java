package capstone.cbcb.dto.user;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class UserUpdateRequestDto {
    private String email;
    private String password;
    private String name;
    private String mycar;
    private String phone_number;
    private String nickname;



}