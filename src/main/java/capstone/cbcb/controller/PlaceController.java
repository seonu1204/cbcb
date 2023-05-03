package capstone.cbcb.controller;

import capstone.cbcb.service.PlaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class PlaceController {

    private final PlaceService placeService;

    @GetMapping("/address")
    public List<Object> getPlaceWithMatchingAddress() {
        return placeService.findAllPlaceJoinAddress();
    }

    @GetMapping("/facility")
    public List<Object> getPlaceWithMatchingFacility() {
        return placeService.findAllPlaceJoinFacility();
    }
}
