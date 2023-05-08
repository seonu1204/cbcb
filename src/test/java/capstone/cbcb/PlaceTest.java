package capstone.cbcb;

import capstone.cbcb.domain.address.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class PlaceTest {

    @Autowired
    private PlaceRepository placeRepository;
    private AddressRepository addressRepository;

//    @Test
//    void testJpa() {
//        List<Place> all = this.placeRepository.findAll();
//    }
//
//    @Test
//    void test2() {
//        Optional<Place> op =this.placeRepository.findById("1");
//        if(op.isPresent()){
//            Place p = op.get();
//            assertEquals("a", p.getPlace_name());
//        }
//    }

//    @Test
//    public void testFindAllPlacesWithMatchingAddress() {
//        List<Place> places = placeRepository.findAllPlacesWithMatchingAddress();
//
//        // 결과가 비어 있지 않은지 확인
//        assertFalse(places.isEmpty());
//
//        // 각 장소의 place_id와 address의 place_id가 일치하는지 확인
//        for (Place place : places) {
//            assertEquals(place.getPlace_id(), place.getAddress().getPlace_id());
//        }
//    }
}


}
