#include <cs50.h>
#include <stdio.h>

int get_cents(void);


int main (void)
{
    int cents = get_cents();

    printf("%i\n",cents);
    int quarters;
    //quarters =
}



int get_cents(void)
{
    // TODO
    int cents;
    do
    {
        cents = get_int("Change owed: ");
    }
    while(cents < 0 || cents > 99);

    return cents;
}