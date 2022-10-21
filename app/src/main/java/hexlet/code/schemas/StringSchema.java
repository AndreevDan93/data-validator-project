package hexlet.code.schemas;

public final class StringSchema extends BaseSchema {

    @Override
    public void assignRequiredPredicate() {
        setRequiredPredicate(value -> value instanceof String && !(value.toString().isBlank()));
    }

    @Override
    public StringSchema required() {
        assignRequiredPredicate();
        addPredicate(0, getRequiredPredicate());
        return this;
    }

    public StringSchema minLength(int minN) {
        addPredicate(value -> !getRequiredPredicate().test(value) || value.toString().length() >= minN);
        return this;
    }

    public StringSchema contains(String contain) {
        addPredicate(value -> !getRequiredPredicate().test(value) || value.toString().contains(contain));
        return this;
    }

}
