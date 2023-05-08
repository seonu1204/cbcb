package capstone.cbcb.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Boolean existsByEmail(String email);

    Optional<User> findByEmail(String email);

//    @Query(value = "update user u set u.name = :name, u.mycar = :mycar, u.phone_number = :phone_number, u.nickname = :nickname where u.email = :email", nativeQuery = true)
//    Optional<User> userUpdate(@Param("name") String name, @Param("mycar") String mycar, @Param("phone_number") String phone_number, @Param("nickname") String nickname, @Param("email") String email);

}
