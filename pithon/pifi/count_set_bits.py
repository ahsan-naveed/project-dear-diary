def getSetBits(n):
    setBits = 0
    while n:
        if  n % 2:
            setBits += 1
        n = n // 2
    return setBits


def main(n):
    totalSetBits = 0
    for i in range(1, n + 1):
        totalSetBits += getSetBits(i)
    return totalSetBits


if __name__ == "__main__":
    print(f'Number of set bits from 1 to 3: {str(main(3))}')
    print(f'Number of set bits from 1 to 6: {str(main(6))}')
    print(f'Number of set bits from 1 to 7: {str(main(7))}')
    print(f'Number of set bits from 1 to 1000000: {str(main(1000000))}')
