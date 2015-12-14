package bitwisedemo;

/*

http://javarevisited.blogspot.com/2013/03/bitwise-and-bitshift-operators-in-java-and-or-xor-left-right-shift-example-tutorial.html

Bitwise Operators
------------------

 ~    //(Bitwise unary NOT) : ~ will change 0 to 1 and 1 to 0
 &    //(Bitwise AND) : AND bitwise operation will return 1 only if both operands are 1, otherwise zero
 |    //(Bitwise OR) : OR will return 1 if any of operand is 1 and zero only if both operands are zeros
 ^    //(Bitwise exclusive OR) : XOR bitwise operation will return 1 if both operands are different
 >>   //(Shift right)
 >>>  //(Shift right zero fill)
 <<   //(Shift left)
 &=   //(Bitwise AND assignment)
 |=   //(Bitwise OR assignment)
 ^=   //(Bitwise exclusive OR assignment)
 >>=  //(Shift right assignment)
 >>>= //(Shift right zero fill assignment)
 <<=  //(Shift left assignment)

Tricky Questions:
----------------
- How to Swap Two Numbers without Temp or Third variable? Ans: By using bitwise operators
- How to check if a Number is Positive or Negative in Java?
- How to reverse String in Java using Iteration and Recursion?

Introduction
-----------------
  Bitwise operators in Java are powerful set of operators which allows
  you to manipulate bits on integral types like int, long, short, bytes and boolean
  data types in Java and cannot be used with floating-point types like float,double.

Boolean - single bit
Char - 16 bit unicode character

Precision
----------
- Precision refers to the maximum number of digits that are present in the number
- eg. 462.25 has the precision 5 and 2 decimal places

Byte,Short,Integer,Long
--------------------------
- Positive numbers in Java are represented as ordinary binary numbers
- Negative numbers in Java are represented as 2's complement of number, why?
- It's done so that addition doesn't need to have any special logic for dealing with negative numbers, just add
- To get number back from 2's complement, just calculate 2's complement and 2's complement and put - sign in the front.


Float(Single-precision),Double(Double-precision)
-------------------------------------------------
A floating-point number has 4 components
 - sign,mantissa(significand),base(radix),exponent
  eg. -9.25x10^2 where sign=-,mantissa=9.25,base=10,exponent=2
  And as per IEEE 754, for Single precision,
 - Mantissa takes 23 bits and it's called the precision of this format
 - Exponent takes 8 bits, and can be negative or positive
 - The mantissa is normalized efore storing. ie. converted to 1.fffffffffffffffffffffff format, f for 0/1
 - This way we don't need to store leading 1 since it's always 1, so we can use all 23 bits to store the fraction,
   so in this way we achieved one extra bit to store, hence the precision is 24.
  And as per IEEE 754, for Double precision,
  - Width 64 bit, exponent 11 bit, mantissa, 52(+1) bit and sign 1 bit

Little Endian and Big Endian
----------------------------
These represents the direction of bytes in a word within CUP arch.
Little Endian: storing less significant bytes first and then ms bytes
Big Endian: stroing ms bytes first and then less significant bytes



 */
public class Main {

    public static void main(String[] args) {
        System.out.println(Float.NaN);
        additionWithoutSubtractionOperator();
        bitShiftTesting();
        swap();
    }

    public static void additionWithoutSubtractionOperator() {
        int a = 7;
        int b = 4;

        int result = (a + (~b + 1));

        System.out.printf("Subtraction:\n%d-%d = %d\n", a, b, result);
    }

    public static void bitShiftTesting() {
        int number = 8; //0000 1000
        System.out.println("Original number : " + number);

        //left shifting bytes with 1 position
        number = number << 1; //should be 16 i.e. 0001 0000

        //equivalent of multiplication of 2
        System.out.println("value of number after left shift: " + number);

        number = -8;
        //right shifting bytes with sign 1 position
        number = number >> 1; //should be 16 i.e. 0001 0000

        //equivalent of division of 2
        System.out.println("value of number after right shift with sign: " + number);

        number = -8;
        //right shifting bytes without sign 1 position
        number = number >>> 1; //should be 16 i.e. 0001 0000

        //equivalent of division of 2
        System.out.println("value of number after right shift with sign: " + number);

    }

    public static void swap() {
        int a = 2; //0010 in binary
        int b = 4; //0100 in binary

        System.out.println("value of a and b before swapping, a: " + a + " b: " + b);

        //swapping value of two numbers without using temp variable and XOR bitwise operator
        a = a ^ b; //now a is 6 and b is 4
        b = a ^ b; //now a is 6 but b is 2 (original value of a)
        a = a ^ b; //now a is 4 and b is 2, numbers are swapped

        System.out.println("value of a and b after swapping using XOR bitwise operation, a: " + a + " b: " + b);
    }
}
