package hexlet.code.schemas;

import java.util.Map;

public final class MapSchema extends BaseSchema {

    @Override
    public boolean notRequired(Object content) {
        return content instanceof Map<?, ?>
                ? this.getPredicates().stream().allMatch(predicate -> predicate.test(content))
                : content == null;
    }

    @Override
    public MapSchema required() {
        this.setRequired(true);
        this.getPredicates().add(0, a -> a instanceof Map<?, ?>);
        return this;
    }

    public MapSchema sizeof(Integer size) {
        this.getPredicates().add(value -> ((Map<String, Object>) value).size() == size);
        return this;
    }

    public MapSchema shape(Map<String, BaseSchema> schemas) {
        this.getPredicates().add(n -> mapValueValidCheck((Map<String, Object>) n, schemas));
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
