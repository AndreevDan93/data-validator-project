package hexlet.code.Schema;


import java.util.function.Predicate;

public class NumberSchema extends BaseSchema {

    @Override
    public NumberSchema required() {
        this.isRequired = true;
        Predicate<?> pr = a -> a instanceof Integer;
        this.predicates.add(pr);
        return this;
    }

    public NumberSchema positive() {
        Predicate<Integer> pr = a -> a > 0;
        this.predicates.add(pr);
        return this;
    }

    public NumberSchema range(Integer min, Integer max) {
        Predicate<Integer> pr = a -> a >= min && a <= max;
        this.predicates.add(pr);
        return this;
    }


}
