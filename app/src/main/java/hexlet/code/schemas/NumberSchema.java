package hexlet.code.schemas;

public final class NumberSchema extends BaseSchema {

    @Override
    public boolean notRequired(Object content) {
        if (content == null) {
            return true;
        } else {
            this.getPredicates().add(0, value -> value instanceof Integer);
            return false;
        }
    }

    @Override
    public NumberSchema required() {
        this.setRequired(true);
        this.getPredicates().add(0, value -> value instanceof Integer);
        return this;
    }

    public NumberSchema positive() {
        this.getPredicates().add(value -> (Integer) value > 0);
        return this;
    }

    public NumberSchema range(Integer min, Integer max) {
        this.getPredicates().add(value -> (Integer) value >= min && (Integer) value <= max);
        return this;
    }
}
