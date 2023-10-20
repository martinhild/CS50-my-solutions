SELECT name FROM people WHERE id =
    (
        SELECT person_id from stars WHERE movie_id =
        (
            SELECT id from movies WHERE year = "2004"
        )
    )


;