SELECT name FROM people WHERE id IN
    (
        SELECT person_id from stars WHERE movie_id IN
        (
            SELECT id from movies WHERE year = "2004"
        )
    )
ORDER BY birth;