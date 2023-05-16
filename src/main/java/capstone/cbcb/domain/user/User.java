package capstone.cbcb.domain.user;

import capstone.cbcb.dto.user.UserUpdateRequestDto;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.transaction.annotation.Transactional;

@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int user_id;

    private String name;
    private String email;
    private String password;
    private String mycar;
    private String phone_number;
    private int eco_lv;
    private String nickname;

    @Builder
    public User(int user_id, String name, String email, String password, String mycar, String phone_number, int eco_lv, String nickname) {
        this.user_id = user_id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.mycar = mycar;
        this.phone_number = phone_number;
        this.eco_lv = eco_lv;
        this.nickname = nickname;

    }

    public void userUpdate(UserUpdateRequestDto userUpdateRequestDto) {
        this.name = userUpdateRequestDto.getName();
        this.mycar = userUpdateRequestDto.getMycar();
        this.phone_number = userUpdateRequestDto.getPhone_number();
        this.nickname = userUpdateRequestDto.getNickname();
    }
}
