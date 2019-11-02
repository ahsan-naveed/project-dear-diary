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

"""
Fraction Class Implementing:
    __init__
    __str__
    __add__
    __mul__
    __truediv__
    __sub__
    __radd__
    __iadd__
    __repr__
    show
    __gt__
    __lt__
    __le__
    __eq__
    __ne__
"""
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
        self.num = ((self.num * value.den) + (value.num * self.den))
        self.den = self.den * value.den

        return Fraction(self.num, self.den)

    def __repr__(self):
        return 'Fraction(' + str(self.num) + '/' + str(self.den) + ')'

#######################################################################
#######################################################################
#######################################################################

"""
LogicGate Class
"""
class LogicGate:
    def __init__(self, n):
        self.label = n
        self.output = None

    def getLabel(self):
        return self.label

    def getOutput(self):
        self.output = self.performGateLogic()
        return self.output

"""
BinaryGate Class Extends LogicGate
"""
class BinaryGate(LogicGate):
    def __init__(self, n):
        LogicGate.__init__(self, n)

        self.pinA = None
        self.pinB = None

    def getPinA(self):
        return (int(input("Enter Pin A input for gate " + self.getLabel() + "-->")))
    
    def getPinB(self):
        return (int(input("Enter Pin B input for gate " + self.getLabel() + "-->")))

"""
UnaryGate Class Extends LogicGate
"""
class UnaryGate(LogicGate):
    def __init__(self, n):
        LogicGate.__init__(self, n)

        self.pin = None

    def getPin(self):
        return (int(input("Enter Pin input for gate" + self.getLabel() + "-->")))

"""
AndGate class Extends BinaryGate
"""
class AndGate(BinaryGate):
    def __init__(self, n):
        super(AndGate, self).__init__(n)

    def performGateLogic(self):
        a = self.getPinA()
        b = self.getPinB()

        if a == 1 and b == 1:
            return 1
        else:
            return 0

"""
OrGate class Extends BinaryGate
"""
class OrGate(BinaryGate):
    def __init__(self, n):
        super(OrGate, self).__init__(n)

    def performGateLogic(self):
        a = self.getPinA()
        b = self.getPinB()

        if a == 1 or b == 1:
            return 1
        else:
            return 0

class NotGate(UnaryGate):
    def __init__(self, n):
        super(NotGate, self).__init__(n)

    def performGateLogic(self):
        if self.getPin() == 1:
            return 0
        else:
            return 1
    
if __name__ == "__main__":
    g1 = AndGate("G1")
    print(g1.getOutput())
