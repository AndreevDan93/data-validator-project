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
        this.setRequired(true);
        this.getPredicates().add(0, value -> value instanceof String && !(value.toString().isBlank()));
        return this;
    }

    public StringSchema minLength(int minN) {
        this.getPredicates().add(value -> value.toString().length() >= minN);
        return this;
    }

    public StringSchema contains(String contain) {
        this.getPredicates().add(value -> value.toString().contains(contain));
        return this;
    }
}
