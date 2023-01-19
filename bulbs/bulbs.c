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
    int binary[8];
    int k;
    int number;
    for (int i = 0; i < length; i++){
        number = decimal_array[i];
        k = 0;
        while (number > 0){
            binary[k] = number % 2;
            number = number/2;
            k++;
        }
        for (int j = 7; j >= 0; j--){
            //printf("%i ", binary[j]);
            print_bulb( binary[j] );
        }
        printf("\n");
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


