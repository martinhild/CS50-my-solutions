#include <cs50.h>
#include <stdio.h>
#include <ctype.h>
#include <string.h>

int count_letters();
int count_words();
int count_sentences();


int main(void)
{

//get Text
string text = get_string("Text: ");
//print number of letters, words, sentences
//printf("%i letters\n", count_letters(text));
//printf("%i words\n", count_words(text));
//printf("%i sentences\n", count_sentences(text));

float letters = (float)count_letters(text);
float words = (float)count_words(text);
float sentences = (float)count_sentences(text);

float l = letters / words * 100;
float s = sentences / words * 100;

float index = 0.0588 * l - 0.296 * s - 15.8;



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
    int sentences = 0;
    for (int i = 0; i < strlen(text); i++)
    {
        if (text[i] == '.' || text[i] == '?' || text[i] == '!')
        {
            sentences++;
        }
    }
    return sentences;
}