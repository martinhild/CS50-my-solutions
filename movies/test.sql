SELECT peoplename
FROM people JOIN stars JOIN movies
ON people.id = stars.person_id AND stars.movie_id = movies.id

SELECT moviesyear = "2004"
ORDER BY people.birth


LIMIT 10

;