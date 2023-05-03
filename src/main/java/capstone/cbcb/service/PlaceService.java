package capstone.cbcb.service;

import capstone.cbcb.domain.place.PlaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PlaceService {

    private final PlaceRepository placeRepository;

    // Place table, Address table join
    public List<Object> findAllPlaceJoinAddress() {
        return placeRepository.findAllPlacesWithMatchingAddress();
    }

    // Place table, Facility join
    public List<Object> findAllPlaceJoinFacility() {
        return placeRepository.findAllPlacesWithMatchingFacility();
    }
}