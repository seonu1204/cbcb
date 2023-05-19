package capstone.cbcb.infra;

import capstone.cbcb.infra.filter.LoginCheckFilter;
import capstone.cbcb.infra.jwt.JwtFactory;
import jakarta.servlet.DispatcherType;
import jakarta.servlet.Filter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
<<<<<<< HEAD
=======
import org.springframework.web.servlet.config.annotation.CorsRegistry;
>>>>>>> f12f1ddbf0c21e5b5ffab39888d3314038a2467e
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Component
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

    private final JwtFactory jwtFactory;

    @Bean
    public FilterRegistrationBean<Filter> logFilter() {
        FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<Filter>();

        filterRegistrationBean.setFilter(new LoginCheckFilter(jwtFactory));	// LogFilter등록
        filterRegistrationBean.setOrder(1);	// 먼저 적용할 필터 순서
        filterRegistrationBean.addUrlPatterns("/*"); //모든 url 다 적용 -> 모든 요청에 다 필터 적용
        filterRegistrationBean.setDispatcherTypes(DispatcherType.REQUEST, DispatcherType.ERROR);
        return filterRegistrationBean;
    }
<<<<<<< HEAD
=======

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")  // 모든 URL에 대해 CORS 설정 적용
                .allowedOrigins("*") // 허용할 오리진 설정
                .allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH") // 허용할 HTTP 메서드 설정
                .allowedHeaders("*"); // 허용할 헤더 설정
    }
>>>>>>> f12f1ddbf0c21e5b5ffab39888d3314038a2467e
}