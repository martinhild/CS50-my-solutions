--cars that left within 10min of 10:15am = 10:10 - 10:20

SELECT *
  FROM bakery_security_logs
 WHERE day = 28
   AND hour = 10
   AND minute >= 10
   AND minute <= 20
LIMIT 10;
--  WHERE month = 7
--    AND day = 28
--    AND street = "Humphrey Street";