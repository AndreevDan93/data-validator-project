package hexlet.code.schemas;

public final class StringSchema extends BaseSchema {

    @Override
    public boolean notRequired(Object content) {
        if (content instanceof String) {
            return this.getPredicates().stream().allMatch(predicate -> predicate.test(content));
        } else {
            return content == null || content.toString().isBlank();
        }
    }

    @Override
    public StringSchema required() {
        setRequiredPredicate(value -> value instanceof String && !(value.toString().isBlank()));
        addPredicate(0, getRequiredPredicate());
        return this;
    }

    public StringSchema minLength(int minN) {
        addPredicate(value -> value.toString().length() >= minN);
        return this;
    }

    public StringSchema contains(String contain) {
        addPredicate(value -> value.toString().contains(contain));
        return this;
    }
}
