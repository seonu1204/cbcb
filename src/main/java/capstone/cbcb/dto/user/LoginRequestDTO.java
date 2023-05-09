package capstone.cbcb.dto.user;

import lombok.Data;
import lombok.NoArgsConstructor;

//DTO 는 대문자가 맞는거에요 !
@Data
@NoArgsConstructor
public class LoginRequestDTO {

    private String email;

    private String password;

}
