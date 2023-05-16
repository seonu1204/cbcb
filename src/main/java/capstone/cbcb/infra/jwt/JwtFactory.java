package capstone.cbcb.infra.jwt;


import capstone.cbcb.dto.user.UserDecodeJWTDTO;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Component
@Slf4j
@RequiredArgsConstructor
public class JwtFactory {


    public static final String REFRESH_TOKEN = "refreshToken";

    public static final String HEADER_ACCESS_TOKEN = "Authorization";

    private static String secret = "[B@2893de87";

    private static String DOMAIN_URL = "http://capstone.com";

    private static String LOGIN_ID = "LOGIN_ID";

    private static String EMAIL = "EMAIL";

    private static String NAME = "NAME";

    private static String USER_ID = "USER_ID";

    private static String MY_CAR = "MY_CAR";

    private static String ECO_LV = "ECO_LV";

    private static String NICK_NAME = "NICK_NAME";

    private static String PHONE_NUMBER = "PHONE_NUMBER";

    private Algorithm generateSign() {
        return Algorithm.HMAC256("jwtProperty.getKey()");

    }

    public String generateAccessToken(int userId, String email, String name, String myCar, int eco_lv, String nickname  ,String phoneNumber) {

        return JWT.create()
                .withIssuer(DOMAIN_URL)
                .withIssuedAt(Date.from(new Date().toInstant()))
                .withExpiresAt(Date.from(LocalDateTime.now().plusHours(30).atZone(ZoneId.systemDefault()).toInstant()))
                .withClaim(USER_ID, String.valueOf(userId))
                .withClaim(EMAIL, email)
                .withClaim(NAME, name)
                .withClaim(MY_CAR, myCar)
                .withClaim(ECO_LV, String.valueOf(eco_lv))
                .withClaim(NICK_NAME, nickname)
                .withClaim(PHONE_NUMBER,phoneNumber)
                .sign(generateSign());
    }

    /**
     * Decode JWT Token
     *
     * @param token : req token
     * @return : AuthUser
     */
    public UserDecodeJWTDTO decodeJwt(final String token) throws Exception {

        DecodedJWT decodedJWT = isValidToken(token)
                .orElseThrow(() -> new Exception());

        String email = decodedJWT.getClaim(EMAIL).asString();
        String userId = decodedJWT.getClaim(USER_ID).asString();
        String name = decodedJWT.getClaim(NAME).asString();
        String myCar = decodedJWT.getClaim(MY_CAR).asString();
        String eco_lv = decodedJWT.getClaim(ECO_LV).asString();
        String nickname = decodedJWT.getClaim(NICK_NAME).asString();
        String phoneNumber = decodedJWT.getClaim( PHONE_NUMBER).asString();

        return UserDecodeJWTDTO.builder()
                .email(email)
                .user_id(Integer.parseInt(userId))
                .name(name)
                .mycar(myCar)
                .eco_lv(Integer.parseInt(eco_lv))
                .nickname(nickname)
                .phone_number(phoneNumber)
                .build();
    }

    /**
     * Validation Token
     *
     * @param token : req Token
     * @return : JWT
     */
    private Optional<DecodedJWT> isValidToken(final String token) throws Exception {

        Algorithm algorithm = this.generateSign();
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT decodedJWT = null;

        try {
            decodedJWT = verifier.verify(token);
        } catch (Exception e) {
            throw new Exception("잘못된 정보의 TOKEN 입니다.");
        }

        return Optional.of(decodedJWT);
    }

    // 현재 유효한 토큰 목록
    private Set<String> validTokens = new HashSet<>();

    // 토큰 추가
    public void addValidToken(String token) {
        validTokens.add(token);
    }

    // 토큰 삭제
    public void removeValidToken(String token) {
        validTokens.remove(token);
    }

    public void invalidateToken(String token, HttpServletRequest request, HttpServletResponse response) {
        removeValidToken(token);

        HttpSession session = request.getSession(false);
        if(session != null) {
            session.invalidate();

            Cookie sessionCookie = new Cookie("JSESSIONID", null);
            sessionCookie.setPath("/");
            sessionCookie.setMaxAge(0);
            response.addCookie(sessionCookie);
        }
    }

}
