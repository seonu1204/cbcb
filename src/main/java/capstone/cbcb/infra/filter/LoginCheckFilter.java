package capstone.cbcb.infra.filter;

import capstone.cbcb.dto.user.UserDecodeJWTDTO;
import capstone.cbcb.infra.jwt.JwtFactory;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.PatternMatchUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


@Slf4j
public class LoginCheckFilter extends OncePerRequestFilter {

    //로그인이 필요하지 않은 페이지 url 요청들
    private static final String[] whitelist = {"/", "/api/user/join", "/api/user/login", "/api/place/*", "/api/chatbot/place"};
    private final String USER = "USER";
    private JwtFactory jwtFactory;

    public LoginCheckFilter(JwtFactory jwtFactory) {
        this.jwtFactory = jwtFactory;
    }

    @SneakyThrows
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String requestURI = httpRequest.getRequestURI();
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        // whitelist 에 있는 uri 는 검증을 하지 않기때문에,
        // isLoginCheckPath 메서드에서 매칭되지 않는 경우만 가져온다.
        // 인증체크 로직 시작
        if (isLoginCheckPath(requestURI)) {

            // JWT TOKEN Check
            String jwtToken = ((HttpServletRequest) request).getHeader("Authorization");

            UserDecodeJWTDTO user = null;
            try {
                user = jwtFactory.decodeJwt(jwtToken);
            } catch (Exception ex) {
                httpResponse.sendError(401, ex.getMessage());
            }


            if (user != null) {

                // 수정된 부분 : 안 넣으면 user 가 세션에 저장이 안 됨
                HttpSession session = httpRequest.getSession(true);
                session.setAttribute(USER, user);

                // 로그인이 되어있다면 다음 단계로 넘어간다.
                filterChain.doFilter(request, response);
            } else {
                return;
            }

            HttpSession session = httpRequest.getSession(false);
            if( session == null || session.getAttribute(USER) == null){
                session.setAttribute(USER, user);
                return;
            }

        } else{
            filterChain.doFilter(request,response);
        }

    }

    /*
     * 화이트 리스트의 경우 인증 체크 x
     * simpleMatch 	: 파라미터 문자열이 특정 패턴에 매칭되는지를 검사함.
     */
    private boolean isLoginCheckPath(String requestURI) {
        return !PatternMatchUtils.simpleMatch(whitelist, requestURI);
        // 매칭이 되지 않을 때 검증을 해야하기 때문에 부정해준다.
    }

}
