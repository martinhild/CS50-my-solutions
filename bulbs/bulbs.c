#include <cs50.h>
#include <stdio.h>
#include <string.h>

const int BITS_IN_BYTE = 8;

void print_bulb(int bit);

int main(void)
{
    // TODO

    //get the message
    string message = get_string("Message: ");

    //count length of the message
    int length = strlen(message);

    //build decimal_array with ascii numbers
    int decimal_array[length];
    for (int i = 0; i < length; i++)
    {
        decimal_array[i] = message[i];
    }

    //create array for binarys
    int binary_array[length][8];

    //fill array with the binarys

    int decimal;
    for(int j = 0; j < length; j++){
        decimal = decimal_array[j];

        while(decimal > 0)
        {
            binary_array[j] = decimal % 2;
            decimal = decimal / 2;
            k++;
        }

    }




}

//function to convert a decimal number to binary


void print_bulb(int bit)
{
    if (bit == 0)
    {
        // Dark emoji
        printf("\U000026AB");
    }
    else if (bit == 1)
    {
        // Light emoji
        printf("\U0001F7E1");
    }
}


