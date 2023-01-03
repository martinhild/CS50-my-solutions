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

    //Loop
    for (int i = 0; i < height; i++)
    {
        width=i+1;
        for (int j = 0; j < height ; j++)
        {
            if( j > i ){
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