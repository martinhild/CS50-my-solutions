# TODO
from cs50 import get_string


def main():
    text = get_string("Text: ")
    letters = count_letters(text)
    words = count_words(text)
    sentences = count_sentences(text)
    L = 100 * letters / words
    coleman_Liau_index = 0.0588 * L - 0.296 * S - 15.8



    print(letters)
    print(words)
    print(sentences)

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

    #period, exclamation point, or question mark indicates the end of a sentence
    end = [".", "!", "?"]
    for c in text:
        if c in end:
            sentences += 1
    return sentences

main()

# 400 buchstaben / 200 wörter