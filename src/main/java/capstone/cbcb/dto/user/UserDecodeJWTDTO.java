package capstone.cbcb.dto.user;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDecodeJWTDTO {

    private int user_id;
    private String name;
    private String email;
    private String password;
    private String mycar;
    private String phone_number;
    private int eco_lv;
    private String nickname;

}
