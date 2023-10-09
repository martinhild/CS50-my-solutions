import csv
import sys


def main():

    # TODO: Check for command-line usage
    if not len(sys.argv) == 3:
        print("Usage: python dna.py data.csv sequence.txt")
        return 0

    # TODO: Read database file into a variable
    csvfile = open(sys.argv[1],"r")
    reader = csv.DictReader(csvfile)

    # TODO: Read DNA sequence file into a variable
    with open(sys.argv[2],"r") as txtfile:
        sequence = txtfile.read()


    # TODO: Find longest match of each STR in DNA sequence
    strs_in_sequence = []
    for i in range(len(reader.fieldnames))[1::]:
        str = reader.fieldnames[i]
        strs_in_sequence.append(longest_match(sequence,str))

    print(strs_in_sequence)

    # TODO: Check database for matching profiles
    names = []
    # for row in reader:
    #     names.append(row['name'])
    #print(names)

    for row in reader:
        for x in range(len(row.values())):
            print(x)



def longest_match(sequence, subsequence):
    """Returns length of longest run of subsequence in sequence."""

    # Initialize variables
    longest_run = 0
    subsequence_length = len(subsequence)
    sequence_length = len(sequence)

    # Check each character in sequence for most consecutive runs of subsequence
    for i in range(sequence_length):

        # Initialize count of consecutive runs
        count = 0

        # Check for a subsequence match in a "substring" (a subset of characters) within sequence
        # If a match, move substring to next potential match in sequence
        # Continue moving substring and checking for matches until out of consecutive matches
        while True:

            # Adjust substring start and end
            start = i + count * subsequence_length
            end = start + subsequence_length

            # If there is a match in the substring
            if sequence[start:end] == subsequence:
                count += 1

            # If there is no match in the substring
            else:
                break

        # Update most consecutive matches found
        longest_run = max(longest_run, count)

    # After checking for runs at each character in seqeuence, return longest run found
    return longest_run


main()
