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
        assertTrue(schema.isValid(null)); //true

        schema.positive();
        assertTrue(schema.isValid(null));
        assertTrue(schema.isValid(5));
        assertFalse(schema.isValid(-5));
        assertFalse(schema.isValid("5")); // false

        schema.required();
        assertFalse(schema.isValid(null)); // false
        assertTrue(schema.isValid(10)); // true
        assertFalse(schema.isValid("5")); // false
        assertTrue(schema.isValid(10)); // true
        assertFalse(schema.isValid(-10)); // false

        schema.range(5, 10);
        assertTrue(schema.isValid(5)); // true
        assertTrue(schema.isValid(10)); // true
        assertFalse(schema.isValid(4)); // false
        assertFalse(schema.isValid(11)); // false
        assertFalse(schema.isValid(null)); //false
    }

    @Test
    void numberSchemaLogicTest() {
        Validator v1 = new Validator();
        NumberSchema workingAge = v1.number();

        workingAge.required().positive().range(18, 65);
        assertFalse(workingAge.isValid(null)); //false
        assertFalse(workingAge.isValid(4)); //false
        assertTrue(workingAge.isValid(18)); //true
        assertTrue(workingAge.isValid(45)); //woman is berry again
        assertTrue(workingAge.isValid(65)); //true
    }

    @Test
    void stringSchemaCaseTest() {
        Validator v = new Validator();

        StringSchema schema = v.string();
        assertTrue(schema.isValid("")); // true
        assertTrue(schema.isValid(null)); // true
        assertFalse(schema.isValid(5)); // false

        schema.required();
        assertTrue(schema.isValid("what does the fox say")); // true
        assertTrue(schema.isValid("hexlet")); // true
        assertFalse(schema.isValid(null)); // false
        assertFalse(schema.isValid("")); // false

        assertTrue(schema.minLength(2).isValid("hexlet")); //true
        assertFalse(schema.minLength(10).isValid("Harry")); //false

        assertTrue(schema.contains("wh").isValid("what does the fox say")); // true
        assertTrue(schema.contains("what").isValid("what does the fox say")); // true
        assertFalse(schema.contains("whatthe").isValid("what does the fox say")); // false
    }

    @Test
    void stringSchemaLogicTest() {
        Validator v = new Validator();

        StringSchema userID = v.string();

        userID.required().minLength(5).contains("id=");
        assertFalse(userID.isValid("id=")); //false
        assertFalse(userID.isValid("123456")); //false
        assertFalse(userID.isValid(null)); //false
        assertTrue(userID.isValid("id=123")); //true
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
        MapSchema characters = v.map();
        Map<String, BaseSchema> checkSchema = Map.of(
                "name", v.string().required().minLength(4).contains(" "),
                "age", v.number().required().positive().range(18, 55),
                "id", v.number().positive().range(11111111, 888888888));


        Map<String, Object> user1 = Map.of(
                "name", "Harry Potter",
                "age", 22,
                "id", 88888888);
        assertTrue(characters.required().sizeof(3).shape(checkSchema).isValid(user1));

        Map<String, Object> user2 = Map.of(
                "name", "Dumbledore",
                "age", 55,
                "id", 11111111);
        assertFalse(characters.isValid(user2));

        Map<String, Object> user3 = Map.of(
                "name", "Dobby",
                "age", 116,
                "id", 88888889);
        assertFalse(characters.isValid(user3));

        Map<String, Object> user4 = Map.of(
                "name", "Hermione O.P. Power",
                "age", 21,
                "faculty", "Gryffindor");
        assertFalse(characters.isValid(user4));

    }
}
