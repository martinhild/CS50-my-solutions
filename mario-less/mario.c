#include <cs50.h>
#include <stdio.h>

int main(void)
{
    int height;
    int width;
    string line;

    //Get user input:
    do
    {
        height = get_int("Enter height: ");
    }
    while (height < 1 || height > 8);

    //Loop from line '0' till line 'height'
    for (int i = 0; i < height; i++)
    {
        //Loop from column '0' till column 'height'
        for (int j = 0; j < height ; j++)
        {
            //decide if printing ' ' or '#'
            if (j < height - i - 1)
            {
                printf(" ");
            }
            else
            {
                printf("#");
            }
        }
        //print new line after one line is finished
        printf("\n");
    }
}