SELECT movie_id FROM stars WHERE people_id = (
    SELECT id from people WHERE name = "Kevin Bacon" AND birth = "1958"
    );