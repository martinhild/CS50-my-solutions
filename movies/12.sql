SELECT movies.title, people.name
FROM people JOIN stars JOIN movies
ON people.id = stars.person_id AND stars.movie_id = movies.id
WHERE people.id = (SELECT id FROM people WHERE id = ("Bradley Cooper"))

ORDER BY movies.title;