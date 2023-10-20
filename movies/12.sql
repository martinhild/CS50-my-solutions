SELECT movies.title, COUNT(*)
FROM people JOIN stars JOIN movies
ON people.id = stars.person_id AND stars.movie_id = movies.id

WHERE

people.name = "Jennifer Lawrence"
OR
people.name = "Bradley Cooper"

GROUP BY movies.title

ORDER BY movies.title;