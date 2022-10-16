package hexlet.code.Schema;

import hexlet.code.Validator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NumberSchemaTest {

    @Test
    void stringSchemaTest() {
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
}