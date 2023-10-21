--cars that left within 10min of 10:15am = 10:10 - 10:20
SELECT *
  FROM bakery_security_logs
 WHERE day = 28
   AND hour = 10
   AND minute >= 10
   AND minute <= 20
   AND activity = "exit";