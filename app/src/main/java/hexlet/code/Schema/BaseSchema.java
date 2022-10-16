package hexlet.code.Schema;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

class BaseSchema {
    List<Predicate<?>> predicates = new ArrayList<>();
    protected boolean isRequired = false;

    protected BaseSchema required() {
        isRequired = true;
        return this;
    }

    protected boolean isValid(Object content) {
        if (isRequired) {
            for (Predicate predicate : predicates) {
                if (!predicate.test(content)) {
                    return false;
                }
            }
        }
        return true;
    }
}


