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
        setRequiredPredicate(a -> a instanceof Integer);
        addPredicate(0, getRequiredPredicate());
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
