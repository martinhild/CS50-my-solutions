SELECT *
  FROM people
  WHERE id IN
    (SELECT person_id
       FROM bank_accounts
      WHERE account_number IN (28500762, 49610011));