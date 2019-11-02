def gcd(x, y):
   """This function implements the Euclidian algorithm
   to find G.C.D. of two numbers"""

   while(y):
       x, y = y, x % y

   return x

def lcm(x, y):
   """This function takes two
   integers and returns the L.C.M."""

   lcm = (x*y)//gcd(x,y)
   return lcm

class Fraction:
    def __init__(self, up,down):
        common = gcd(up, down)
        self.num = up // common
        self.den = down // common

    def __str__(self):
        frac_str = ''

        if self.num == 0:
            frac_str = '0'
        elif self.den == 1:
            frac_str = str(self.num)
        else:
            frac_str = str(self.num) + '/' + str(self.den)
        
        return frac_str

    def show(self):
        if self.num == 0:
            print('0')
        elif self.den == 1:
            print(self.num)
        else:
            print(self.num, '/', self.den)
    
    def __add__(self, value):
        new_num = ((self.num * value.den) + (value.num * self.den))
        new_den = self.den * value.den

        return Fraction(new_num, new_den)
    
    def __sub__(self, value):
        new_num = ((self.num * value.den) - (value.num * self.den))
        new_den = self.den * value.den

        return Fraction(new_num, new_den)

    def __mul__(self, value):
        return Fraction(self.num * value.num, self.den * value.den)

    def __truediv__(self, value):
        return Fraction(self.num * value.den, self.den * value.num)

    def __eq__(self, other):
        first_num = self.num * other.den
        second_num = other.num * self.den

        return first_num == second_num
    
    def __gt__(self, value):
        return self.num * value.den > self.den * value.num
    
    def __lt__(self, value):
        return self.num * value.den < self.den * value.num
    
    def __le__(self, value):
        return self.num * value.den <= self.den * value.num
    
    def __ne__(self, value):
        return self.num * value.den != self.den * value.num

    def __radd__(self, value):
        return Fraction.__add__(self, value)

    def __iadd__(self, value):
        pass

    def __repr__(self):
        pass



if __name__ == "__main__":
    frac1 = Fraction(2, 2)
    frac2 = Fraction(1, 2)
    
    print(frac1 + Fraction(1, 1))
