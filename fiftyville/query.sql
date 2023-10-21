
--cat query.sql | sqlite3 fiftyville.db

SELECT *
  FROM crime_scene_reports
 WHERE description LIKE "%duck%";