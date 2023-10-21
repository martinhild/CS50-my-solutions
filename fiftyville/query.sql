-- search for drivers and check ATM activity
SELECT *
  FROM atm_transactions
  WHERE atm_location = "Leggett Street"
    AND day = 28
  LIMIT 10;