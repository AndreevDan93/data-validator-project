package hexlet.code.schemas;

public final class NumberSchema extends BaseSchema {

    @Override
    public void assignRequiredPredicate() {
        setRequiredPredicate(a -> a instanceof Integer);
    }

    @Override
    public NumberSchema required() {
        assignRequiredPredicate();
        addPredicate(0, getRequiredPredicate());
        return this;
    }

    public NumberSchema positive() {
        addPredicate(value -> !getRequiredPredicate().test(value) || (Integer) value > 0);
        return this;
    }

    public NumberSchema range(Integer min, Integer max) {
        addPredicate(value -> !getRequiredPredicate().test(value)
                || ((Integer) value >= min && (Integer) value <= max));
        return this;
    }


}
