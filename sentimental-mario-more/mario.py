# TODO
def main():
    height = get_height()
    for line in range(height):
        print((height - (line + 1)) * " " + (line + 1) * "#" + "  " + (line + 1) * "#")


# get height from user input
def get_height():
    while True:
        try:
            height = int(input("Height: "))
            if 0 < height < 9:
                return height
        except ValueError:
            pass


main()
