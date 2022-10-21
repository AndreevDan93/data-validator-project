package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public abstract class BaseSchema {
    private final List<Predicate<Object>> predicates = new ArrayList<>();
    private Predicate<Object> requiredPredicate;

    public final Predicate<Object> getRequiredPredicate() {
        return requiredPredicate;
    }

    public final void setRequiredPredicate(Predicate<Object> predicate) {
        this.requiredPredicate = predicate;
    }


    public final void addPredicate(Predicate<Object> predicate) {
        this.predicates.add(predicate);
    }

    public final void addPredicate(Integer index, Predicate<Object> predicate) {
        this.predicates.add(index, predicate);
    }


    public abstract BaseSchema required();

    public abstract void assignRequiredPredicate();

    public final boolean isValid(Object content) {
        if (this.requiredPredicate == null) {
            assignRequiredPredicate();
        }
        return predicates.stream().allMatch(predicate -> predicate.test(content));
    }
}


