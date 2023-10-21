SELECT *
  FROM atm_transactions
  WHERE atm_location = "Leggett Street"
    AND transaction_type = "withdraw"
    AND day = 28
  LIMIT 10;