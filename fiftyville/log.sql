-- Keep a log of any SQL queries you execute as you solve the mystery.

--take a look at crime_scene_reports
SELECT *
  FROM crime_scene_reports;


--look for crimes on a July 28th Humphrey Street
SELECT *
  FROM crime_scene_reports
 WHERE month = 7
   AND day = 28
   AND street = "Humphrey Street";
-- Theft took place at 10:15am Humphrey Street
-- bakery. Interviews were conducted today with three witnesses who
-- were present at the time – each of their interview transcripts
-- mentions the bakery.
-- id of crime: 295


SELECT *
  FROM crime_scene_reports
 WHERE description LIKE "%duck%";
 -- see that there is only this one report with "duck" in description

-- search for the interviews. We already know that "each of their
-- interview transcripts mentions the bakery".
SELECT *
  FROM interviews
 WHERE month = 7
   AND day = 28
   AND transcript LIKE "%bakery%";
-- Ruth:
-- look for cars that left the parking lot within ten minures of the theft
-- Eugene:
-- before he arrived at bakery, the thief withdrew money from ATM
-- Raymond:
-- when thief was leaving bakery he had a call for less than 1min
-- "earliest flight out of Fiftyville tomorrow"
-- asked person on the other end to purchase the flight ticket


--cars that left within 10min of 10:15am = 10:10 - 10:20
SELECT *
  FROM bakery_security_logs
 WHERE day = 28
   AND hour = 10
   AND minute >= 10
   AND minute <= 20
   AND activity = "exit";
-- license plates: 5P2BI95 94KL13X 6P58WS2 4328GD8 G412CB7


-- look at license plates
SELECT *
  FROM people
  WHERE license_plate IN ("5P2BI95", "94KL13X", "6P58WS2", "4328GD8", "G412CB7");
-- IDs of people: 221103 243696 398010 467400 686048


-- bank account numbers of these people:
SELECT account_number
  FROM bank_accounts
 WHERE person_id IN (221103, 243696, 398010, 467400, 686048);


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
-- RESULT: only two account numbers: 28500762 and 49610011

-- look for their personal id by looking at bank accounts:
SELECT person_id
  FROM bank_accounts
  WHERE account_number IN (28500762, 49610011);

--people:
SELECT *
  FROM people
  WHERE id IN
    (SELECT person_id
       FROM bank_accounts
      WHERE account_number IN (28500762, 49610011));
-- +--------+-------+----------------+-----------------+---------------+
-- |   id   | name  |  phone_number  | passport_number | license_plate |
-- +--------+-------+----------------+-----------------+---------------+
-- | 467400 | Luca  | (389) 555-5198 | 8496433585      | 4328GD8       |
-- | 686048 | Bruce | (367) 555-5533 | 5773159633      | 94KL13X       |
-- +--------+-------+----------------+-----------------+---------------+

-- phone calls of those numbers on that day less than 1 min:
SELECT *
  FROM phone_calls
 WHERE caller IN ("(389) 555-5198" , "(367) 555-5533")
   AND day = 28
   AND duration < 60;
-- only one RESULT: caller: (367) 555-5533 and receiver: (375) 555-8161


--search receiver
SELECT *
  FROM people
 WHERE phone_number = "(375) 555-8161";
 -- result: id= 864400 name = Robin

-- caller/theft: Bruce, id=686048 and receiver/accomplice: Robin, id=864400

--passport numbers:
SELECT passport_number
  FROM people
 WHERE id IN (686048, 864400);

-- look for a flight in passengers with one of those passport numbers:
SELECT *
  FROM passengers
 WHERE passport_number IN
  (SELECT passport_number
     FROM people
    WHERE id IN (686048, 864400));
-- only one flight: flight_id = 36

--look at flight
SELECT *
  FROM flights
 WHERE id = 36;
 --origin_airport_id = 8 and destination_airport_id = 4

--get airport ids:
SELECT origin_airport_id, destination_airport_id
  FROM flights
 WHERE id = 36;

-- Airports:
SELECT *
  FROM airports
 WHERE id IN (8, 4);

 -- Origin Airport: CSF, Fiftyville Regional Airport, Fiftyville
 -- destination airport: LGA, LaGuardia Airport, New York City