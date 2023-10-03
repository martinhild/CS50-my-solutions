# TODO
from cs50 import get_string


def main():
    number = get_number()
    if not LuhnsAlgorithm(number):
        print("INVALID")
        return
    check_provider(number)


# check if a credit card number is valid
def LuhnsAlgorithm(number):
    # add every other digit multiplied by 2 to a string, starting with the number’s second-to-last digit,
    string_1 = ""
    for x in number[len(number) - 2 :: -2]:
        string_1 += str((int(x) * 2))

    # sum of string_1's digits
    sum = 0
    for x in string_1:
        sum += int(x)

    # add the other digits of the user's number to sum
    for x in number[len(number) - 1 :: -2]:
        sum += int(x)

    # check if sum's last digit is 0
    if sum % 10 == 0:
        return True
    else:
        return False


# gets number from user
def get_number():
    return get_string("Number: ")


# checks the provider
def check_provider(number):
    # Visa numbers start with 4
    if number[0] == "4":
        print("VISA")
        return
    # American Express numbers start with 34 or 37
    american_express = ["34", "37"]
    if number[0] + number[1] in american_express:
        print("AMEX")
        return
    # MasterCard numbers start with 51, 52, 53, 54, or 55
    master_card = ["51", "52", "53", "54", "55"]
    if number[0] + number[1] in master_card:
        print("MASTERCARD")
        return
    print("INVALID")
    return


main()

# 4062901840
# American Express uses 15-digit numbers, MasterCard uses 16-digit numbers, and Visa uses 13- and 16-digit numbers. 