package capstone.cbcb.domain.place;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QPlace is a Querydsl query type for Place
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPlace extends EntityPathBase<Place> {

    private static final long serialVersionUID = 1879443691L;

    public static final QPlace place = new QPlace("place");

    public final StringPath address = createString("address");

    public final StringPath camp_env = createString("camp_env");

    public final StringPath contact = createString("contact");

    public final StringPath facils = createString("facils");

    public final StringPath operation_day = createString("operation_day");

    public final StringPath page_url = createString("page_url");

    public final StringPath place_id = createString("place_id");

    public final NumberPath<Integer> place_like = createNumber("place_like", Integer.class);

    public final StringPath placeName = createString("placeName");

    public final StringPath reservation = createString("reservation");

    public final StringPath season = createString("season");

    public final StringPath theme = createString("theme");

    public QPlace(String variable) {
        super(Place.class, forVariable(variable));
    }

    public QPlace(Path<? extends Place> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPlace(PathMetadata metadata) {
        super(Place.class, metadata);
    }

}

