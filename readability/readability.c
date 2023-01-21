#include <cs50.h>
#include <stdio.h>
#include <ctype.h>
int count_letters();

int main(void)
{

//get Text
string text = get_string("Text: ");
//print text
printf("Text: %s\n", text);


}


int count_letters(string text)
{
    int letters = 0;
    for (int i = 0; i < text.getLength(); i++)
    {
        if (isalpha(text[i]))
        {
            letters++;
        }
    }
    return letters;
}