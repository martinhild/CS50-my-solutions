-- -- search for drivers and check ATM activity
-- SELECT *
--   FROM atm_transactions
--   LIMIT 10;

  SELECT *
  FROM interviews
 WHERE month = 7
   AND day = 28
   AND transcript LIKE "%bakery%";