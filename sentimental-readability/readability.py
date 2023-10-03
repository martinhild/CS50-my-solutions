# TODO
from cs50 import get_string


def main():

    text = get_string("Text: ")
    L = letters(text)
    print(L)

def letters(text):
    letters = 0
    for c in text:
        if "A" <= c <= "Z" or "a" <= c <= "z":
            letters += 1
    return letters

main()