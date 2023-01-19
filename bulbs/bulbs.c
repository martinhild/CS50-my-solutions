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

    //loop through decimal_array,
    //every loop, build an array for the binary number (binary number is reverse)
    //print elements of this array in reverse order
    int binary[length][8];

    
    for (int i = 0; i < length; i++){

    }


}


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


