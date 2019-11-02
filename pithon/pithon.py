def gcd(a, b):
    while a%b != 0:
        prev_a = a
        prev_b = b

        a = prev_b
        b = prev_a % prev_b
    return b


class Fraction:
    def __init__(self, up,down):
        self.num = up
        self.den = down

    def __str__(self):
        return str(self.num) + "/" + str(self.den)

    def show(self):
        print(self.num, "/", self.den)
    
    def __add__(self, other_fraction):
        new_num = ((self.num * other_fraction.den) + (other_fraction.num * self.den))
        new_den = self.den * other_fraction.den
        common = gcd(new_num, new_den)
        return Fraction(new_num//common, new_den//common)
    
    
    



def main():
    print(gcd(10, 20))

if __name__ == 'main':
    print("hello world")
    main()