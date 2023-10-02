# TODO
from cs50 import get_int
from cs50 import get_string


def main():
    number = get_number()

    # for x in number:
    #     print(x)

    for i in [0,1,2]:
        print("hi")


def get_number():
    return get_string("Number: ")


main()