package capstone.cbcb.domain.place;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QUserPlace is a Querydsl query type for UserPlace
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserPlace extends EntityPathBase<UserPlace> {

    private static final long serialVersionUID = 594297312L;

    public static final QUserPlace userPlace = new QUserPlace("userPlace");

    public final StringPath address = createString("address");

    public final StringPath descript = createString("descript");

    public final StringPath latitude = createString("latitude");

    public final StringPath longitude = createString("longitude");

    public final NumberPath<Integer> report = createNumber("report", Integer.class);

    public final StringPath tags = createString("tags");

    public final NumberPath<Integer> user_id = createNumber("user_id", Integer.class);

    public final NumberPath<Integer> userPlaceId = createNumber("userPlaceId", Integer.class);

    public final NumberPath<Integer> userPlaceLike = createNumber("userPlaceLike", Integer.class);

    public final StringPath userPlaceName = createString("userPlaceName");

    public QUserPlace(String variable) {
        super(UserPlace.class, forVariable(variable));
    }

    public QUserPlace(Path<? extends UserPlace> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUserPlace(PathMetadata metadata) {
        super(UserPlace.class, metadata);
    }

}

