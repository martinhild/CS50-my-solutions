#include <cs50.h>
#include <stdio.h>

int get_cents(void);


int main (void)
{
    int cents = get_cents();

    printf("cents: %i\n",cents);

    int quarters;
    quarters = cents%25;
    printf("quarters: %i", quarters);
}



int get_cents(void)
{
    // TODO
    int cents;
    do
    {
        cents = get_int("Change owed: ");
    }
    while(cents < 1 || cents > 99);

    return cents;
}