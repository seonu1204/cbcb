package capstone.cbcb.domain.bookmark;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class BookmarkId {

    @Column(name = "user_id")
    private int userId;

    @Column(name = "place_id")
    private String placeId;
}