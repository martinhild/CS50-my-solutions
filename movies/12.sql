SELECT movies.title, COUNT(*) as cnt
FROM people JOIN stars JOIN movies
ON people.id = stars.person_id AND stars.movie_id = movies.id
WHERE
    (
    people.name = "Jennifer Lawrence"
    OR
    people.name = "Bradley Cooper"
    )
GROUP BY movies.title
having cnt =2
;