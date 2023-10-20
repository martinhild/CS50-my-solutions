-- SELECT name FROM people WHERE id =
--     (
--     SELECT person_id FROM stars WHERE movie_id =
--         (
        SELECT movie_id FROM stars WHERE person_id =
            (
            SELECT id FROM people WHERE name = "Kevin Bacon"
            )
    --     )

    -- )
LIMIT 10;

SELECT person_id FROM stars WHERE movie_id = "83833";
SELECT title FROM movies WHERE id = "83833";