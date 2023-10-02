# TODO
from cs50 import get_int
from cs50 import get_string


def main():
    number = get_number()

    # add every other digit, starting with the number’s second-to-last digit, to a new list
    for x in number[len(number)-2 :: -2]:
        print(x)



def get_number():
    return get_string("Number: ")


main()
