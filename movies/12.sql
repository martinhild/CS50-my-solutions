SELECT movies.title, people.name
FROM people JOIN stars JOIN movies
ON people.id = stars.person_id AND stars.movie_id = movies.id
WHERE movie.id = (SELECT starsid FROM people WHERE name = ("Bradley Cooper"))

ORDER BY movies.title;