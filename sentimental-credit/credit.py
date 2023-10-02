# TODO
from cs50 import get_int
from cs50 import get_string


def main():
    # get a number from user
    number = get_number()

    # add every other digit multiplied by 2 to a string, starting with the number’s second-to-last digit,
    string_1 = ""
    for x in number[len(number)-2 :: -2]:
        string_1 += str((int(x) * 2))

    # sum of string_1's digits
    sum = 0
    for x in string_1:
        sum += int(x)

    # add the other digits of the user's number to sum
    for x in number[len(number)-1 :: -2]:
        sum += int(x)

    print(sum)





def get_number():
    return get_string("Number: ")


main()
