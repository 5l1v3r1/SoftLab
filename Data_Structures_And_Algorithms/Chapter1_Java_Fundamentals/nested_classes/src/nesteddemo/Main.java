package nesteddemo;

/**
 * Nested classes
 * --------------
 * - A nested class is a class withing an another class.
 * - A nested class is a member of its enclosing class.
 *
 * Why use nested class ?
 * -----------------------
 * - It is a way of logically grouping classes that are only used in one place.
 * - It increases encapsulation.
 * - It can lead to more readable and maintainable codes.
 *
 * Types of Nested class
 * ---------------------
 * [1] Static nested class
 * [2] Non-static nested class (Inner class)
 *     [2.1] Local class
 *     [2.2] Anonymous class
 *
 * Static Nested Class
 * --------------------
 * A static nested class interacts with the instance members of it's outer class and othr class just like any
 * top-level class. So a static nested class is behaviourally a top-level class that has been nested in another
 * top-level class for packaging convenience. It can be public,private,protected,package default. It is access
 * by using following syntax: OuterClass.InnerClass cls = new OuterClass.InnerClass();
 *
 * Non-static class(Inner class)
 * -----------------------------
 * An instance of inner class can exist only withing an instance of outer class and has direct access to the
 * methods and members of its enclosing class.
 *         Local class
 *         -------------
 *         Classes that are defined in a block, which is a group of zero or more statements, between balanced
 *         braces. We can find local classes defined in the body of method,for-loop,if-clause,block of code.
 *
 *         Anonymous class
 *         ---------------
 *         It enables us to define and instantiate the class at the same time.
 *         Use these type of classes if you need to use a class only once.
 *         These are used as an expressions.
 *
 *
 */

public class Main {
    interface Message{
        public void printMessage(String message);
    }

    public static void main(String[] args) {
	    // Static nested class
        Validator.PhoneNumberValidator phoneNumberValidator =  new Validator.PhoneNumberValidator("21515115415");
        phoneNumberValidator.validatePhoneNumber();
        // Non-static nested class,inner class
        Validator validator = new Validator();

        Validator.ZipCodeValidator zipCodeValidator = validator.new ZipCodeValidator("435");
        zipCodeValidator.validateZipCode();

        // Local class
        class PostalCodeValidator{
            private  int a = 3;
            private  String postalCode = null;

            public PostalCodeValidator(String postalCode) {
                this.postalCode = postalCode;
            }

            public boolean validatePostalCode(){
                System.out.println("Executing postal code validator: " + a);
                if (a == 2 && postalCode != null && postalCode.length() > 3){
                    return true;
                }
                return false;
            }
        }

        PostalCodeValidator postalCodeValidator = new PostalCodeValidator("564");
        postalCodeValidator.validatePostalCode();

        // Anonymous class
        Message message = new Message() {
            @Override
            public void printMessage(String message) {
                System.out.println("Message: " + message);
            }
        };

        message.printMessage("This is from Anonymous class");
    }
}
