# TODO
def main():
    height = get_height
    print(height)

#get height from user input
def get_height():
    while True:
        try:
            height = int(input("Height: "))
            return height
        except ValueError:
            print("No number")


main()