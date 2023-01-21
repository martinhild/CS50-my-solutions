#include <cs50.h>
#include <stdio.h>
#include <ctype.h>
#include <string.h>

int count_letters();

int main(void)
{

//get Text
string text = get_string("Text: ");
//print number ofletters
printf("%i letters\n", count_letters(text));


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