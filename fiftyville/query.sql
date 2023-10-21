-- look at bank accounts
SELECT *
  FROM bank_accounts
 WHERE person_id IN (221103, 243696, 398010, 467400, 686048)
LIMIT 30;