package capstone.cbcb.service;

import capstone.cbcb.domain.place.UserPlaceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Slf4j
@RequiredArgsConstructor // Repository를 주입하기 위해 사용
@Service
public class UserPlaceService {

    private final UserPlaceRepository userPlaceRepository;



}
