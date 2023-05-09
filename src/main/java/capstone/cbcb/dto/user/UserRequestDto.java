package capstone.cbcb.dto.user;

import capstone.cbcb.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserRequestDto {
    private int user_id;

    private String name;
    private String email;
    private String password;
    private String mycar;
    private String phone_number;
    private int eco_lv ;
    private String nickname;


    public UserRequestDto(User entity) {
        this.user_id = entity.getUser_id();
        this.name = entity.getName();
        this.email = entity.getEmail();
        this.password = entity.getPassword();
        this.mycar = entity.getMycar();
        this.phone_number = entity.getPhone_number();
        this.eco_lv = entity.getEco_lv();
        this.nickname = entity.getNickname();

    }

}
