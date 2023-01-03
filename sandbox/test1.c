#include <cs50.h>
#include <stdio.h>

int main (void)
{
    printf("sasdasdd\n");
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