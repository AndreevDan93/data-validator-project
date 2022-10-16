package hexlet.code.Schema;

import hexlet.code.Validator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class NumberSchemaTest {

    @Test
    void numberSchemaTest() {
        Validator v = new Validator();

        NumberSchema schema = v.number();

        Assertions.assertTrue(schema.isValid(null)); // true

        schema.required();

        Assertions.assertFalse(schema.isValid(null)); // false
        Assertions.assertTrue(schema.isValid(10)); // true
        Assertions.assertFalse(schema.isValid("5")); // false

        Assertions.assertTrue(schema.positive().isValid(10)); // true
        Assertions.assertFalse(schema.isValid(-10)); // false

        schema.range(5, 10);

        Assertions.assertTrue(schema.isValid(5)); // true
        Assertions.assertTrue(schema.isValid(10)); // true
        Assertions.assertFalse(schema.isValid(4)); // false
        Assertions.assertFalse(schema.isValid(11)); // false
    }

    @Test
    void numberSchemaTest1() {
        Validator v = new Validator();

        NumberSchema schema = v.number();

        Assertions.assertTrue(schema.required().positive().range(5, 10).isValid(5));
        Assertions.assertFalse(schema.isValid(4));
        Assertions.assertTrue(schema.isValid(6));
    }


}
