SELECT DISTINCT movies.title, people.name
FROM people JOIN stars JOIN movies
ON people.id = stars.person_id AND stars.movie_id = movies.id
WHERE people.name IN ("Bradley Cooper", "Jennifer Lawrence")

ORDER BY movies.title;