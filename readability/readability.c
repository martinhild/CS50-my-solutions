#include <cs50.h>
#include <stdio.h>
#include <ctype.h>
#include <string.h>

int count_letters();
int count_words();

int main(void)
{

//get Text
string text = get_string("Text: ");
//print number ofletters
printf("%i letters\n", count_letters(text));
printf("%i words\n", count_words(text));


}


int count_letters(string text)
{
    int letters = 0;
    for (int i = 0; i < strlen(text); i++)
    {
        if (isalpha(text[i]))
        {
            letters++;
        }
    }
    return letters;
}

int count_words(string text)
{
    int words = 0;
    for (int i = 0; i < strlen(text); i++)
    {
        if (isspace(text[i]))
        {
            words++;
        }
    }
    return words + 1;
}

int count_sentences(string text)
{
    int s = 0;
    for (int i = 0; i < strlen(text); i++)
    {
        if (isspace(text[i]))
        {
            words++;
        }
    }
    return words + 1;
}