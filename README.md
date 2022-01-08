# presto-query-extractor

## Usage

```
$ java -jar presto-query-extractor-all.jar
Value for option --element should be always provided in command line.
Usage: presto-query-extractor options_list
Options:
    --file, -f [] -> SQL file { String }
    --query, -q [] -> Query string { String }
    --element, -e -> Element to extract (always required) { Value should be one of [table, comparisonexpression, inpredicate] }
    --help, -h -> Usage info

```

### ComparisonExpression

```
$ java -jar presto-query-extractor-all.jar -f ../../projects/presto-query-extractor/presto-query-extractor/src/test/resources/02.sql -e comparisonexpression
{
    "comparisonExpressions": [
        {
            "right": "nation.regionkey",
            "left": "region.regionkey"
        }
    ]
}
```

### InPredicates

```
$ java -jar presto-query-extractor-all.jar -q "SELECT * FROM Customers WHERE Country IN ('Germany', 'France', 'UK')" -e inpredicate
{
    "inPredicates": [
        {
            "value": "Country",
            "valueList": "('Germany', 'France', 'UK')"
        }
    ]
}
```

### Table

```
$ java -jar presto-query-extractor-all.jar -f ../../projects/presto-query-extractor/presto-query-extractor/src/test/resources/01.sql -e table
{
    "tables": [
        "t"
    ]
}
```

## Reference

https://github.com/prestodb/presto/issues/8910

## Test

```
./gradlew test
```