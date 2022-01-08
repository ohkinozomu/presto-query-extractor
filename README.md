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
$ cat PATH/TO/02.sql
SELECT name
FROM nation
WHERE EXISTS (SELECT * FROM region WHERE region.regionkey = nation.regionkey)
$ java -jar presto-query-extractor-all.jar -f PATH/TO/02.sql -e comparisonexpression
{
    "comparisonExpressions": [
        {
            "left": "region.regionkey",
            "right": "nation.regionkey"
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
$ cat PATH/TO/01.sql
SELECT a, b
FROM (
    SELECT a, MAX(b) AS b FROM t
    WHERE a = c
    GROUP BY a
) AS x
$ java -jar presto-query-extractor-all.jar -f PATH/TO/01.sql -e table
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