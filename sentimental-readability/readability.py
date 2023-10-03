# TODO
from cs50 import get_string


def main():
    text = get_string("Text: ")
    letters = letters(text)
    words = words(text)
    # sentences = sentences(text)

    L = 100 * letters(text) / words(text)

    print(letters)
    print(words)

def letters(text):
    letters = 0
    for c in text:
        if "A" <= c <= "Z" or "a" <= c <= "z":
            letters += 1
    return letters

def words(text):
    words = 0
    for c in text:
        if c == " ":
            words += 1
    return words +1

main()

# 400 buchstaben / 200 wörter