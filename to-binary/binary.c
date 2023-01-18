#include <cs50.h>
#include <stdio.h>
#include <string.h>

int main(void)
{
    int decimal = get_int("Integer: ");

    int binary_array[8];

    int i = 0;

    while(decimal > 0)
    {
        binary_array[i] = decimal % 2;
        decimal = decimal / 2;
        i++;
    }

    for(int j = 7; j >= 0 ; j--)
    {
        printf("%i ", binary_array[j]);
    }

}