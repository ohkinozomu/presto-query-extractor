SELECT a, b
FROM (
    SELECT a, MAX(b) AS b FROM t
    WHERE a = c
    GROUP BY a
) AS x