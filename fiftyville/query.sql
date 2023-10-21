
--cat query.sql | sqlite3 fiftyville.db

SELECT *
  FROM crime_scene_reports
 WHERE month = 7
   AND day = 28
   AND street = "Humphrey Street"
 LIMIT 10;