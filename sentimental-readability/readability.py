# TODO
from cs50 import get_string


def main():
    text = get_string("Text: ")
    letters = count_letters(text)
    words = count_words(text)
    # sentences = sentences(text)

    L = 100 * letters / words

    print(letters)
    print(words)

def count_letters(text):
    letters = 0
    for c in text:
        if "A" <= c <= "Z" or "a" <= c <= "z":
            letters += 1
    return letters

def count_words(text):
    words = 0
    for c in text:
        if c == " ":
            words += 1
    return words +1

main()

# 400 buchstaben / 200 wörter