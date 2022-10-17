package hexlet.code.Schema;

import java.util.Map;
import java.util.function.Predicate;

public class MapSchema extends BaseSchema {

    @Override
    public MapSchema required() {
        this.isRequired = true;
        Predicate<?> pr = a -> a instanceof Map<?, ?>;
        this.predicates.add(pr);
        return this;
    }

    public MapSchema sizeof(Integer size) {
        Predicate<Map<?, ?>> pr = a -> a.size() == size;
        this.predicates.add(pr);
        return this;
    }

    public MapSchema shape(Map<String, BaseSchema> shames) {
        Predicate<Map<?, ?>> pr = n -> getPredicateComplexValid((Map<String, Object>) n, shames);
        predicates.add(pr);
        return this;
    }

    private boolean getPredicateComplexValid(Map<String, Object> content, Map<String, BaseSchema> schemes) {
        for (Map.Entry<?, ?> entry : ((Map<?, ?>) content).entrySet()) {
            if (!schemes.containsKey(entry.getKey())) {
                return false;
            }

            BaseSchema schema = schemes.get(entry.getKey());

            if (!schema.isValid(entry.getValue())) {
                return false;
            }
        }
        
        return true;
    }

}
