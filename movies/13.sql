

SELECT movie_id FROM stars WHERE person_id = (
    SELECT id from people WHERE name = "Kevin Bacon" AND birth = "1958"
    ) l;