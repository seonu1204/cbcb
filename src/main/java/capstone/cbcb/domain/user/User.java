package capstone.cbcb.domain.user;

import capstone.cbcb.dto.user.UserUpdateRequestDto;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

@AllArgsConstructor
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

    public void userUpdate(UserUpdateRequestDto userUpdateRequestDto) {
        this.name = name;
        this.mycar = mycar;
        this.phone_number = phone_number;
        this.nickname = nickname;
    }
}
