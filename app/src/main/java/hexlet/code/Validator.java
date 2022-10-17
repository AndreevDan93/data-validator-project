package hexlet.code;

import hexlet.code.Schema.MapSchema;
import hexlet.code.Schema.NumberSchema;
import hexlet.code.Schema.StringSchema;

public final class Validator {
    public StringSchema string() {
        return new StringSchema();
    }

    public NumberSchema number() {
        return new NumberSchema();
    }

    public MapSchema map() {
        return new MapSchema();
    }
}
