package hexlet.code.schemas;

import hexlet.code.Validator;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MapValidatorTest {

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
        assertTrue(schema.isValid(human3)); // false

        Map<String, Object> human4 = new HashMap<>();
        human4.put("name", "Valya");
        human4.put("age", -5);
        assertTrue(schema.isValid(human4)); // false
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
                "name", "Hurry Potter",
                "age", 22,
                "id", 88888888);
        assertTrue(schema.required().sizeof(3).shape(checkSchema).isValid(user1));

        Map<String, Object> user2 = Map.of(
                "name", "Damboldor",
                "age", 55,
                "id", 11111111);
        assertFalse(schema.isValid(user2));

        Map<String, Object> user3 = Map.of(
                "name", "Dobby",
                "age", 116,
                "id", 88888889);
        assertFalse(schema.isValid(user3));

        Map<String, Object> user4 = Map.of(
                "name", "Germiona O.P. Power",
                "age", 21,
                "faculty", "Gryffindor");
        assertFalse(schema.isValid(user4));

    }

}
