SELECT people.name
FROM people JOIN stars JOIN movies
ON people.id = stars.person_id AND stars.movie_id = movies.id
WHERE people.name = "Kevin Bacon"
LIMIT 10;