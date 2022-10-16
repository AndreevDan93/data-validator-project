package hexlet.code.Schema;

import java.util.function.Predicate;

public class StringSchema extends BaseSchema {

    @Override
    public StringSchema required() {
        this.isRequired = true;
        Predicate<?> pr1 = a -> a instanceof String;
        Predicate<String> pr2 = a -> !a.isBlank();
        predicates.add(pr1);
        predicates.add(pr2);
        return this;
    }

    public StringSchema minLength(int minN) {
        Predicate<String> pr = a -> a.length() >= minN;
        predicates.add(pr);
        return this;
    }

    public StringSchema contains(String contain) {
        Predicate<String> pr = a -> a.contains(contain);
        predicates.add(pr);
        return this;
    }

}
