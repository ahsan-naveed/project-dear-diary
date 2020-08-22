def toBinary(n):
    binaryString = ''
    while n:
        mod = n % 2
        binaryString += str(mod)
        n = n // 2
    return binaryString


def counter(binaryStr):
    ones = 0
    for s in binaryStr:
        if s == '1':
            ones += 1
    return ones


def main(n):
    totalOnes = 0
    for i in range(1, n + 1):
        totalOnes += counter(toBinary(i))
    return totalOnes


if __name__ == "__main__":
    print(f'Number of set bits from 1 to 3: {str(main(3))}')
    print(f'Number of set bits from 1 to 6: {str(main(6))}')
    print(f'Number of set bits from 1 to 7: {str(main(7))}')
    print(f'Number of set bits from 1 to 8: {str(main(8))}')
