#include <cs50.h>
#include <stdio.h>

int main(void)
{
    int height;
    int width;
    do
    {
        height = get_int("Enter height: ");
    }
    while (height < 1 || height > 8);
    printf("%i\n", height);


    for (int i = 0; i < height; i++)
    {
        width=i+1;
        for (int j = 0; j < width ; width++)
        {
            printf("#");
        }
    }
}