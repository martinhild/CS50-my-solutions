// Implements a dictionary's functionality

#include <ctype.h>
#include <stdbool.h>

#include "dictionary.h"

// Libraries added by me
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <strings.h>

// Variables added by me
int numberOfWords = 0;

// Represents a node in a hash table
typedef struct node
{
    char word[LENGTH + 1];
    struct node *next;
} node;

// TODO: Choose number of buckets in hash table
const unsigned int N = 26;

// Hash table
node *table[N]; // malloc?????

// Returns true if word is in dictionary, else false
bool check(const char *word)
{
    // TODO
    node *cursor = table[hash(word)];

    while (cursor != NULL)
    {
        if (strcasecmp(word, cursor->word) == 0)
        {
            return true;
        }
        cursor = cursor->next;
    }
    return false;
}

// Hashes word to a number
unsigned int hash(const char *word)
{
    // TODO: Improve this hash function
    unsigned int x = toupper(word[0]) - 'A';
    return x;
}

// Loads dictionary into memory, returning true if successful, else false
bool load(const char *dictionary)
{
    FILE *file = fopen(dictionary, "r");
    if (file == NULL)
    {
        printf("Could not open file");
        fclose(file);
        return false;
    }
    int scan;
    char new_word[LENGTH + 1];
    while (1)
    {
        // load a word and check for end of file
        scan = fscanf(file, "%s", new_word);
        if (scan == EOF)
        {
            fclose(file);
            return true;
        }

        // create new node
        node *new = malloc(sizeof(node));
        if (new == NULL)
        {
            printf("Error: node *new = NULL");
            fclose(file);
            return false;
        }
        strcpy(new->word, new_word);
        unsigned int hashcode = hash(new_word);
        new->next = table[hashcode];
        table[hashcode] = new;
        numberOfWords++;
    }
}

// Returns number of words in dictionary if loaded, else 0 if not yet loaded
unsigned int size(void)
{
    // TODO
    return numberOfWords;
}

// Unloads dictionary from memory, returning true if successful, else false
bool unload(void)
{
    // TODO
    for (int i = 0; i < N; i++)
    {
        node *cursor = table[i];

        if (cursor != NULL)
        {
            do
            {
                node *tmp = cursor;
                cursor = cursor->next;
                free(tmp);
            }
            while (cursor != NULL);
        }
    }
    return true;
}