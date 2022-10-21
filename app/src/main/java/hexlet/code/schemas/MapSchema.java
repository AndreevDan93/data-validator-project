package hexlet.code.schemas;

import java.util.Map;

public final class MapSchema extends BaseSchema {

    @Override
    public void assignRequiredPredicate() {
        if (getRequiredPredicate() == null) {
            setRequiredPredicate(a -> a instanceof Map<?, ?>);
        }
    }

    @Override
    public MapSchema required() {
        assignRequiredPredicate();
        addPredicate(0, getRequiredPredicate());
        return this;
    }

    public MapSchema sizeof(Integer size) {
        addPredicate(value -> !getRequiredPredicate().test(value) || ((Map<String, Object>) value).size() == size);
        return this;
    }

    public MapSchema shape(Map<String, BaseSchema> schemas) {
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
