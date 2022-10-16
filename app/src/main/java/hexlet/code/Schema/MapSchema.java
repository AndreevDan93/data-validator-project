package hexlet.code.Schema;

import java.util.Map;

public class MapSchema {
    private boolean isRequired = false;
    private boolean isSizeOf = false;
    private Integer size;


    public MapSchema required() {
        isRequired = true;
        return this;
    }

    public MapSchema sizeof(Integer size) {
        isSizeOf = true;
        this.size = size;
        return this;
    }


    public boolean isValid(Object content) {
        if (isRequired) {
            if (!(content instanceof Map<?, ?>)) {
                return false;
            }
        }

        if (isSizeOf) {
            Map<?, ?> map = (Map<?, ?>) content;
            return map.size() == size;
        }

        return true;
    }
}
