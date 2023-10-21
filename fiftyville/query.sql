
--cat query.sql | sqlite3 fiftyville.db

SELECT *
  FROM interviews
 WHERE description LIKE "%duck%";