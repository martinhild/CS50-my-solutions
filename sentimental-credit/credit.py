# TODO
from cs50 import get_int
from cs50 import get_string


def main():
    number = get_number()

    for x in number[0:3:1]:
        print(x)


def get_number():
    return get_string("Number: ")


main()