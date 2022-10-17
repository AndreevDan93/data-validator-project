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
проверяет строковые данные, числовые и map обьекты,<br>
а также внутренние данные Map  String и Integer типа
</h3>

<h3>
пример String данных:
</h3>

<pre class="box">
Validator v = new Validator();

StringSchema schema = v.string();

schema.isValid(""); // true
schema.isValid(null); // true

schema.required();

schema.isValid("what does the fox say"); // true
schema.isValid("hexlet"); // true
schema.isValid(null); // false
schema.isValid("");; // false

schema.contains("what").isValid("what does the fox say"); // true
schema.contains("whatthe").isValid("what does the fox say"); // false

schema.isValid("what does the fox say"); // false
// уже false, так как добавлена ещё одна проверка contains("whatthe")
</pre>

<h3>пример Integer данных:</h3>

<pre class="box">
Validator v = new Validator();

NumberSchema schema = v.number();

schema.isValid(null); // true

schema.required();

schema.isValid(null); // false
schema.isValid(10) // true
schema.isValid("5"); // false

schema.positive().isValid(10); // true
schema.isValid(-10); // false

schema.range(5, 10);

schema.isValid(5); // true
schema.isValid(10); // true
schema.isValid(4); // false
schema.isValid(11); // false
</pre>

<h3>пример Map:</h3>

<pre class="box">
Validator v = new Validator();

MapSchema schema = v.map();

schema.isValid(null); // true

schema.required();

schema.isValid(null) // false
schema.isValid(new HashMap()); // true
Map<String, String> data = new HashMap<>();
data.put("key1", "value1");
schema.isValid(data); // true

schema.sizeof(2);

schema.isValid(data);  // false
data.put("key2", "value2");
schema.isValid(data); // true
</pre>

<h3>пример комплексной валидации Map:</h3>
<pre class="box">

// shape - позволяет описывать валидацию для значений объекта Map по ключам.
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
schema.required().sizeof(3).shape(checkSchema).isValid(user1); /true

Map<String, Object> user2 = Map.of(
    "name", "Damboldor",
    "age", 55,
    "id", 11111111);
schema.isValid(user2); /false

Map<String, Object> user3 = Map.of( 
    "name", "Dobby", 
    "age", 116,
    "id", 88888889);
schema.isValid(user3); /false

Map<String, Object> user4 = Map.of(
    "name", "Germiona O.P. Power",
    "age", 21,
    "id", 12345678);
schema.isValid(user4); /true
</pre>
</body>

