package hexlet.code;

import hexlet.code.Schema.Schema;
import hexlet.code.Schema.StringSchema;

public class Validator {
    StringSchema schema;
    public StringSchema string() {
        this.schema = new StringSchema();
        return this.schema;
    }
}
