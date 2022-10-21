package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public abstract class BaseSchema {
    private List<Predicate<Object>> predicates = new ArrayList<>();
    private Predicate<Object> requiredPredicate;

    public Predicate<Object> getRequiredPredicate() {
        return requiredPredicate;
    }

    public void setRequiredPredicate(Predicate<Object> requiredPredicate) {
        this.requiredPredicate = requiredPredicate;
    }


    public void addPredicate(Predicate<Object> predicate) {
        this.predicates.add(predicate);
    }

    public void addPredicate(Integer index, Predicate<Object> predicate) {
        this.predicates.add(index, predicate);
    }


    public BaseSchema required() {
        assignRequiredPredicate();
        addPredicate(0, getRequiredPredicate());
        return this;
    }

    public abstract void assignRequiredPredicate();

    public boolean isValid(Object content) {
        assignRequiredPredicate();
        return predicates.stream().allMatch(predicate -> predicate.test(content));
    }
}


