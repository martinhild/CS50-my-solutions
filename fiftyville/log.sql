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
-- get info:
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
-- get info:
-- Ruth:
-- look for cars that left the parking lot within ten minures of the theft
-- Eugene:
-- before he arrived at bakery, the thief withdrew money from ATM
-- Raymond:
-- when thief was leaving bakery he had a call for less than 1min
-- "earliest flight out of Fiftyville tomorrow"
-- asked person on the other end to purchase the flight ticket


-- cars that left parking lot within 10min of the theft/10:15am