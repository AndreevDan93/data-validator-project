package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public abstract class BaseSchema {
    protected List<Predicate<?>> predicates = new ArrayList<>();
    protected boolean isRequired = false;

    public abstract BaseSchema required();

    public final boolean isValid(Object content) {
        if (content == null) {
            return !isRequired;
        }

        for (Predicate predicate : predicates) {
            if (!predicate.test(content)) {
                return false;
            }
        }
        return true;
    }
}


