SELECT * FROM stars WHERE person_id =
    (SELECT id FROM people
    WHERE people.name = "Kevin Bacon")
LIMIT 10;