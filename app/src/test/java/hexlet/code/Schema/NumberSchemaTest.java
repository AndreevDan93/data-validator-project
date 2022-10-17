package hexlet.code.Schema;

import hexlet.code.Validator;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;


class NumberSchemaTest {

    @Test
    void numberSchemaCaseTest() {
        Validator v = new Validator();

        NumberSchema schema = v.number();

        assertTrue(schema.isValid(null)); // true

        schema.required();

        assertFalse(schema.isValid(null)); // false
        assertTrue(schema.isValid(10)); // true
        assertFalse(schema.isValid("5")); // false

        assertTrue(schema.positive().isValid(10)); // true
        assertFalse(schema.isValid(-10)); // false

        schema.range(5, 10);

        assertTrue(schema.isValid(5)); // true
        assertTrue(schema.isValid(10)); // true
        assertFalse(schema.isValid(4)); // false
        assertFalse(schema.isValid(11)); // false
    }

    @Test
    void numberSchemaLogicTest() {
        Validator v1 = new Validator();
        NumberSchema schema = v1.number();

        assertTrue(schema.required().positive().range(5, 10).isValid(5));
        assertFalse(schema.isValid(4));
        assertTrue(schema.isValid(6));

    }


}
