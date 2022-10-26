package hexlet.code.schemas;

public final class NumberSchema extends BaseSchema {

    @Override
    public boolean notRequired(Object content) {
        if (content instanceof Integer) {
            return this.getPredicates().stream().allMatch(predicate -> predicate.test(content));
        } else {
            return content == null;
        }
    }

    @Override
    public NumberSchema required() {
        this.setRequired(true);
        addPredicate(0, value -> value instanceof Integer);
        return this;
    }

    public NumberSchema positive() {
        addPredicate(value -> (Integer) value > 0);
        return this;
    }

    public NumberSchema range(Integer min, Integer max) {
        addPredicate(value -> (Integer) value >= min && (Integer) value <= max);
        return this;
    }
}
