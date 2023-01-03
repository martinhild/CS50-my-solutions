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

    //Loop through lines
    for (int i = 0; i < height; i++)
    {

        for (int j = 0; j < height ; j++)
        {
            if( j < height - i - 1 ){
                printf(".");
            }
            else
            {
                printf("#");
            }
        }
        printf("\n");
    }
}