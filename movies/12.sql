SELECT movies.title, COUNT(*)
FROM people JOIN stars JOIN movies
ON people.id = stars.person_id AND stars.movie_id = movies.id
WHERE
(
people.name = "Jennifer Lawrence"
AND
people.name = "Bradley Cooper"
)
GROUP BY movies.title

;