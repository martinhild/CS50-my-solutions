SELECT movies.title, people.name
FROM people JOIN stars JOIN movies
ON people.id = stars.person_id AND stars.movie_id = movies.id
WHERE

movies.id = (select stars.person_id = (SELECT id FROM people WHERE name = "Bradley Cooper")
AND
movies.id = (select stars.person_id = (SELECT id FROM people WHERE name = "Jennifer Lawrence")

ORDER BY movies.title;