# TODO
from cs50 import get_string


def main():
    text = get_string("Text: ")
    letters = count_letters(text)
    words = count_words(text)
    sentences = count_sentences(text)
    l = 1.0 * letters / words * 100
    coleman_Liau_index = 0.0588 * l - 0.296 * sentences - 15.8

    print(f"Letters: ", letters)
    print(f"Words: ", words)
    print(f"l: ", l)
    print(f"Sentences: ", sentences)

    print (coleman_Liau_index)


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


def count_sentences(text):
    sentences = 0
    end = [".", "!", "?"]
    for c in text:
        if c in end:
            sentences += 1
    return sentences


main()
