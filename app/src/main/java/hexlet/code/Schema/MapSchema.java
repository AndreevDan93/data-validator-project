package hexlet.code.Schema;

import java.util.Map;
import java.util.function.Predicate;

public class MapSchema extends BaseSchema {

    @Override
    public MapSchema required() {
        isRequired = true;
        Predicate<?> pr = a -> a instanceof Map<?, ?>;
        predicates.add(pr);
        return this;
    }

    public MapSchema sizeof(Integer size) {
        Predicate<Map<?, ?>> pr = a -> a.size() == size;
        predicates.add(pr);
        return this;
    }


}
