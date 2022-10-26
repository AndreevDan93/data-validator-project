package hexlet.code.schemas;

import java.util.Map;

public final class MapSchema extends BaseSchema {
    private Map<String, BaseSchema> schemas;

    @Override
    public boolean notRequired(Object content) {
        if (content instanceof Map<?, ?>) {
            return mapValueValidCheck((Map<String, Object>) content, schemas)
                    && this.getPredicates().stream().allMatch(predicate -> predicate.test(content));
        } else {
            return true;
        }
    }

    @Override
    public MapSchema required() {
        setRequiredPredicate(a -> a instanceof Map<?, ?>);
        addPredicate(0, getRequiredPredicate());
        return this;
    }

    public MapSchema sizeof(Integer size) {
        addPredicate(value -> ((Map<String, Object>) value).size() == size);
        return this;
    }

    public MapSchema shape(Map<String, BaseSchema> insideSchemas) {
        this.schemas = insideSchemas;
        addPredicate(n -> mapValueValidCheck((Map<String, Object>) n, schemas));
        return this;
    }

    private boolean mapValueValidCheck(Map<String, Object> content, Map<String, BaseSchema> schemes) {
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
