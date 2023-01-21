#include <cs50.h>
#include <stdio.h>
#include <string.h>

const int BITS_IN_BYTE = 8;

void print_bulb(int bit);
void decToBinary (int n);

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

    //print decimal array for testing
    for(int i = 0; i < length; i++)
    {
        printf("%i\n", decimal_array[i]);
    }



    for(int k = 0; k < length; k++)
    {
        decToBinary (decimal_array[k]);
    }

}

//function for building binary array and printing it in reverse
void decToBinary (int n)
{
    int binary_array[8];
    int i = 0;
    while(n > 0)
    {
        binary_array[i] = n % 2;
        n = n / 2;
        i++;
    }

    for (int j = 7; j >= 0; j--){
        printf("%i ", binary_array[j]);
        //print_bulb(binary[j]);
    }
    printf("\n");
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


