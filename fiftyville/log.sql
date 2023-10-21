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