
--cat query.sql | sqlite3 fiftyville.db

SELECT *
  FROM interviews
 WHERE month = 7
   AND day = 28
   AND transcript LIKE "%bakery%";