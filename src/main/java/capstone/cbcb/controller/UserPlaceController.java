package capstone.cbcb.controller;


import capstone.cbcb.service.PlaceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
public class UserPlaceController {

    private final PlaceService placeService;

}
