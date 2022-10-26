### Hexlet tests and linter status:
[![Actions Status](https://github.com/AndreevDan93/java-project-78/workflows/hexlet-check/badge.svg)](https://github.com/AndreevDan93/java-project-78/actions)
[![Maintainability](https://api.codeclimate.com/v1/badges/e593145430a26e2a40a8/maintainability)](https://codeclimate.com/github/AndreevDan93/java-project-78/maintainability)
[![Test Coverage](https://api.codeclimate.com/v1/badges/e593145430a26e2a40a8/test_coverage)](https://codeclimate.com/github/AndreevDan93/java-project-78/test_coverage)
![Java CI](https://github.com/AndreevDan93/java-project-78/workflows/Java%20CI/badge.svg)


<body>

<h1>
Программа валидирует данные по критериям
</h1>

<h3>
Проверяет данные Integer, String и Map типа<br>
а также внутренние данные Map  String и Integer типа
</h3>

<h3>
Пример String данных:
</h3>

<pre class="box">
Validator v = new Validator();

StringSchema schema = v.string();
schema.isValid(""); // true
schema.isValid(null); // true
(schema.isValid(5); // false

schema.required();
schema.isValid("what does the fox say"); // true
schema.isValid("hexlet"); // true
schema.isValid(null); // false
schema.isValid(""); // false

schema.minLength(2).isValid("hexlet"); //true
schema.minLength(10).isValid("Harry"); //false

schema.contains("wh").isValid("what does the fox say"); // true
schema.contains("what").isValid("what does the fox say"); // true
schema.contains("whatthe").isValid("what does the fox say"); // false

or complex:

Validator v = new Validator();

StringSchema userID = v.string();

userID.required().minLength(5).contains("id=");
userID.isValid("id="); //false
userID.isValid("123456"); //false
userID.isValid(null); //false
userID.isValid("id=123"); //true

</pre>


<h3>Пример Integer данных:</h3>

<pre class="box">
Validator v = new Validator();

NumberSchema schema = v.number();
schema.isValid(null); //true

schema.positive();
schema.isValid(null);
schema.isValid(5);
schema.isValid(-5);
schema.isValid("5"); // false

schema.required();
schema.isValid(null); // false
schema.isValid(10); // true
schema.isValid("5"); // false
schema.isValid(10); // true
schema.isValid(-10); // false

schema.range(5, 10);
schema.isValid(5); // true
schema.isValid(10); // true
schema.isValid(4); // false
schema.isValid(11); // false
schema.isValid(null); //false

or complex:

Validator v1 = new Validator();
NumberSchema workingAge = v1.number();

workingAge.required().positive().range(18, 65);
workingAge.isValid(null); //false
workingAge.isValid(4); //false
workingAge.isValid(18); //true
workingAge.isValid(45); //woman is berry again
workingAge.isValid(65); //true
</pre>

<h3>Пример Map данных:</h3>

<pre class="box">
Validator v = new Validator();

MapSchema schema = v.map();

schema.isValid(null); // true

schema.required();
schema.isValid(null); // false
schema.isValid(new HashMap<>()); // true
Map<String, String> data = new HashMap<>();
data.put("key1", "value1");
schema.isValid(data); // true

schema.sizeof(2);
schema.isValid(data);  // false
data.put("key2", "value2");
schema.isValid(data); // true

or complex:

// shape - позволяет описывать валидацию для значений объекта Map по ключам.
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
schema.required().sizeof(3).shape(checkSchema).isValid(user1); /true

Map<String, Object> user2 = Map.of(
    "name", "Dumbledore",
    "age", 55,
    "id", 11111111);
schema.isValid(user2); /false

Map<String, Object> user3 = Map.of( 
    "name", "Dobby", 
    "age", 116,
    "id", 88888889);
schema.isValid(user3); /false

Map<String, Object> user4 = Map.of(
    "name", "Hermione O.P. Power",
    "age", 21,
    "id", 12345678);
schema.isValid(user4); /true
</pre>
</body>

