# TODO
from cs50 import get_int
from cs50 import get_string


def main():
    # get number from user
    number = get_number()

    # add every other digit multiplied by 2 to a list, starting with the number’s second-to-last digit,
    string1 = ""
    for x in number[len(number)-2 :: -2]:
        string1 += (int(x) * 2)
    print(string1)
    # add digits together
    # sum_1 = sum(list_1)
    # print(sum_1)



def get_number():
    return get_string("Number: ")


main()
