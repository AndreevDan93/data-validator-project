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
а также внутренние данные HashMap состоящие из String и Integer
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

<h3>пример HashMap:</h3>
<pre class="box">
MapSchema schema = v.map();

// shape - позволяет описывать валидацию для значений объекта Map по ключам.
Map<String, BaseSchema> schemas = new HashMap<>();
schemas.put("name", v.string().required());
schemas.put("age", v.number().positive());
schema.shape(schemas);

Map<String, Object> human1 = new HashMap<>();
human1.put("name", "Kolya");
human1.put("age", 100);
schema.isValid(human1); // true

Map<String, Object> human2 = new HashMap<>();
human2.put("name", "Maya");
human2.put("age", null);
schema.isValid(human2); // true

Map<String, Object> human3 = new HashMap<>();
human3.put("name", "");
human3.put("age", null);
schema.isValid(human3); // false

Map<String, Object> human4 = new HashMap<>();
human4.put("name", "Valya");
human4.put("age", -5);
schema.isValid(human4); // false
</pre>
</body>

