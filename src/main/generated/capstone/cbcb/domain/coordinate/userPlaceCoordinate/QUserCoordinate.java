package capstone.cbcb.domain.coordinate.userPlaceCoordinate;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QUserCoordinate is a Querydsl query type for UserCoordinate
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserCoordinate extends EntityPathBase<UserCoordinate> {

    private static final long serialVersionUID = 1626478462L;

    public static final QUserCoordinate userCoordinate = new QUserCoordinate("userCoordinate");

    public final StringPath latitude = createString("latitude");

    public final StringPath longitude = createString("longitude");

    public final NumberPath<Integer> userplace_id = createNumber("userplace_id", Integer.class);

    public QUserCoordinate(String variable) {
        super(UserCoordinate.class, forVariable(variable));
    }

    public QUserCoordinate(Path<? extends UserCoordinate> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUserCoordinate(PathMetadata metadata) {
        super(UserCoordinate.class, metadata);
    }

}

