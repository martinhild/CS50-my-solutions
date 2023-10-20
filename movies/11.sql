SELECT DISTINCT movies.title
FROM people JOIN stars JOIN movies JOIN ratings
ON people.id = stars.person_id AND stars.movie_id = movies.id
AND movies.id = ratings.movie_id
WHERE people.name = "Chadwick Boseman"
ORDER BY ratings.rating DESC
LIMIT 5
;