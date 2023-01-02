#include <stdio.h>
#include <cs50.h>

int main(void)
{
    //ask for user input
    string name = get_string("What's your name?\n");

    printf("hello, %s\n", name);
}