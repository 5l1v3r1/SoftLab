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



 */
public class Main {

    public static void main(String[] args) {
        additionWithoutSubtractionOperator();
    }

    public static void additionWithoutSubtractionOperator(){
        int  a = 7;
        int b = 4;

        int result = (a+(~b+1));

        System.out.printf("Subtraction:\n%d-%d = %d",a,b,result);
    }
}
