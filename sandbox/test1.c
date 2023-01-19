#include <cs50.h>
#include <stdio.h>

int main (void)
{

    int x = get_int( "Nummer: " );

    if( x < 5 )
    {
        printf( "kleiner\n" );
    }
    if( x > 5)
    {
        printf( "größer\n" );

    }
}