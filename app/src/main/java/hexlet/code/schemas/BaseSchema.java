package hexlet.code.schemas;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Data
public abstract class BaseSchema {
    private final List<Predicate<Object>> predicates = new ArrayList<>();
    private Predicate<Object> requiredPredicate;

    public final void addPredicate(Predicate<Object> predicate) {
        this.predicates.add(predicate);
    }

    public final void addPredicate(Integer index, Predicate<Object> predicate) {
        this.predicates.add(index, predicate);
    }


    public abstract BaseSchema required();

    public abstract boolean notRequired(Object content);

    public final boolean isValid(Object content) {
        if (this.requiredPredicate == null) {
            return notRequired(content);
        } else {
            return predicates.stream().allMatch(predicate -> predicate.test(content));
        }
    }
}


