#include <cs50.h>
#include <stdio.h>

int main(void)
{
    int height;
    do
    {
        height = get_int("Enter height: ");
    }
    while (height < 1 || height > 8);
    printf("%i\n", height);
}