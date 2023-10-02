def main():
    LuhnsAlgorithm("4062901840")

# check if a credit card number is valid
def LuhnsAlgorithm(number):
    # add every other digit multiplied by 2 to a string, starting with the number’s second-to-last digit,
    string_1 = ""
    for x in number[len(number) - 2 :: -2]:
        string_1 += str((int(x) * 2))

    print(string_1)

    # sum of string_1's digits
    sum = 0
    for x in string_1:
        sum += int(x)

    # add the other digits of the user's number to sum
    for x in number[len(number) - 1 :: -2]:
        sum += int(x)

    # check if sum's last digit is 0
    if sum % 10 == 0:
        return True
    else:
        return False

main()