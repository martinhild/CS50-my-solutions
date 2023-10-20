SELECT name FROM people WHERE id =

(SELECT person_id FROM stars WHERE movie_id =
    (SELECT movie_id FROM stars WHERE person_id =
        (SELECT id FROM people
        WHERE people.name = "Kevin Bacon"
        )
    )
)
LIMIT 10;