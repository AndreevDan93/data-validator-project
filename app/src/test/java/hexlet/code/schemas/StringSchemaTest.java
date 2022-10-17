package hexlet.code.schemas;

import hexlet.code.Validator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class StringSchemaTest {

    @Test
    void stringSchemaCaseTest() {
        Validator v = new Validator();

        StringSchema schema = v.string();

        assertTrue(schema.isValid("")); // true
        assertTrue(schema.isValid(null)); // true

        schema.required();

        assertTrue(schema.isValid("what does the fox say")); // true
        assertTrue(schema.isValid("hexlet")); // true
        assertFalse(schema.isValid(null)); // false
        assertFalse(schema.isValid("")); // false

        assertTrue(schema.minLength(2).isValid("hexlet"));
        assertFalse(schema.minLength(10).isValid("Harry"));

        assertTrue(schema.contains("wh").isValid("what does the fox say")); // true
        assertTrue(schema.contains("what").isValid("what does the fox say")); // true
        assertFalse(schema.contains("whatthe").isValid("what does the fox say")); // false

        assertFalse(schema.isValid("what does the fox say")); // false
// уже false, так как добавлена ещё одна проверка contains("whatthe")
    }

    @Test
    void stringSchemaLogicTest() {
        Validator v = new Validator();
        StringSchema schema = v.string();

        assertTrue(schema.required().minLength(5).contains("id").isValid("id12345"));
        assertFalse(schema.isValid("id="));
        assertFalse(schema.isValid("123456"));
        assertFalse(schema.isValid(null));

    }

}
