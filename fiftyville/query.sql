-- their ATM activity
SELECT *
  FROM atm_transactions
  WHERE atm_location = "Leggett Street"
    AND transaction_type = "withdraw"
    AND day = 28
    AND account_number IN
    (SELECT account_number
        FROM bank_accounts
       WHERE person_id IN (221103, 243696, 398010, 467400, 686048));