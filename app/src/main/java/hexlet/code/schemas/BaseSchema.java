package hexlet.code.schemas;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Data
public abstract class BaseSchema {
    private final List<Predicate<Object>> predicates = new ArrayList<>();
    private boolean isRequired = false;

    public abstract BaseSchema required();

    public abstract boolean notRequired(Object content);

    public final boolean isValid(Object content) {
        if (!isRequired) {
            if (notRequired(content)) {
                return true;
            }
        }
        return predicates.stream().allMatch(predicate -> predicate.test(content));
    }
}


