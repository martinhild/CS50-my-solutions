
--cat query.sql | sqlite3 fiftyville.db

SELECT *
  FROM bakery_security_logs
 WHERE day = 28
   AND hour = 10
   AND minute >= 5
   AND minute >= 5
   AND minute <= 20
LIMIT 10;
--  WHERE month = 7
--    AND day = 28
--    AND street = "Humphrey Street";