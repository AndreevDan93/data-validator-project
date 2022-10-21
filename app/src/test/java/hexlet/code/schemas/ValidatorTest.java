package hexlet.code.schemas;

import hexlet.code.Validator;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ValidatorTest {

    @Test
    public void numberSchemaCaseTest() {
        Validator v = new Validator();

        NumberSchema schema = v.number();
        assertTrue(schema.positive().isValid(null));

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
        assertFalse(schema.isValid(null));
    }

    @Test
    void numberSchemaLogicTest() {
        Validator v1 = new Validator();
        NumberSchema schema = v1.number();

        assertTrue(schema.required().positive().range(5, 10).isValid(5));
        assertFalse(schema.isValid(4));
        assertTrue(schema.isValid(6));

    }

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

    @Test
    void mapSchemaCaseTest() {
        Validator v = new Validator();

        MapSchema schema = v.map();

        assertTrue(schema.isValid(null)); // true

        schema.required();

        assertFalse(schema.isValid(null)); // false
        assertTrue(schema.isValid(new HashMap<>())); // true

        Map<String, String> data = new HashMap<>();
        data.put("key1", "value1");

        assertTrue(schema.isValid(data)); // true

        schema.sizeof(2);

        assertFalse(schema.isValid(data));  // false

        data.put("key2", "value2");

        assertTrue(schema.isValid(data)); // true
    }

    @Test
    void mapSchemaComplexCaseTest() {
        Validator v = new Validator();

        MapSchema schema = v.map();

// shape - позволяет описывать валидацию для значений объекта Map по ключам.
        Map<String, BaseSchema> schemas = new HashMap<>();
        schemas.put("name", v.string().required());
        schemas.put("age", v.number().positive());
        schema.shape(schemas);

        Map<String, Object> human1 = new HashMap<>();
        human1.put("name", "Kolya");
        human1.put("age", 100);
        assertTrue(schema.isValid(human1)); // true

        Map<String, Object> human2 = new HashMap<>();
        human2.put("name", "Maya");
        human2.put("age", null);
        assertTrue(schema.isValid(human2)); // true

        Map<String, Object> human3 = new HashMap<>();
        human3.put("name", "");
        human3.put("age", null);
        assertFalse(schema.isValid(human3)); // false

        Map<String, Object> human4 = new HashMap<>();
        human4.put("name", "Valya");
        human4.put("age", -5);
        assertFalse(schema.isValid(human4)); // false
    }

    @Test
    void mapSchemaLogicTest() {
        Validator v = new Validator();
        MapSchema schema = v.map();
        Map<String, BaseSchema> checkSchema = Map.of(
                "name", v.string().required().minLength(4).contains(" "),
                "age", v.number().required().positive().range(18, 55),
                "id", v.number().positive().range(11111111, 888888888));


        Map<String, Object> user1 = Map.of(
                "name", "Harry Potter",
                "age", 22,
                "id", 88888888);
        assertTrue(schema.required().sizeof(3).shape(checkSchema).isValid(user1));

        Map<String, Object> user2 = Map.of(
                "name", "Dumbledore",
                "age", 55,
                "id", 11111111);
        assertFalse(schema.isValid(user2));

        Map<String, Object> user3 = Map.of(
                "name", "Dobby",
                "age", 116,
                "id", 88888889);
        assertFalse(schema.isValid(user3));

        Map<String, Object> user4 = Map.of(
                "name", "Hermione O.P. Power",
                "age", 21,
                "faculty", "Gryffindor");
        assertFalse(schema.isValid(user4));

    }
}