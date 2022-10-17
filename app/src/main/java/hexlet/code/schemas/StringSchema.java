package hexlet.code.schemas;

import java.util.function.Predicate;

public final class StringSchema extends BaseSchema {

    @Override
    public StringSchema required() {
        this.isRequired = true;
        Predicate<?> pr1 = a -> a instanceof String;
        Predicate<String> pr2 = a -> !a.isBlank();
        this.predicates.add(0, pr1);
        this.predicates.add(pr2);
        return this;
    }

    public StringSchema minLength(int minN) {
        Predicate<String> pr = a -> a.length() > minN;
        this.predicates.add(pr);
        return this;
    }

    public StringSchema contains(String contain) {
        Predicate<String> pr = a -> a.contains(contain);
        this.predicates.add(pr);
        return this;
    }

}
