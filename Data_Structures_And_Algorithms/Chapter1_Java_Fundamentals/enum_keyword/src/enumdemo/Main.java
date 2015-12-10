package enumdemo;

/**
 * what is?
 * ----------
 * A Java Enum is a special Java type used to define collections of constants.
 * More precisely,a Java enum type is a special kind of Java class.
 * An enum can contain constants,members,methods/constructor etc. Java enums were added in Java 5.
 *
 * Instance creation
 * --------------------
 * You call an enum method via a reference to one of the constant values since Instance cannot be
 * created using new operator because enum constructors are implicitly/must be private
 * An instance of Enum in Java is created when any Enum constants are first called or referenced in code
 *
 * Inheritance
 * ------------
 * Java enums extend the java.lang.Enum class implicitly, so your enum types cannot extend another class,
 * But can implement others,Enum in Java can implement the interface
 *
 * Which comes first?
 * --------------------
 * If a Java enum contains fields and methods, the definition of fields and methods must always come
 * after the list of constants in the enum.
 * Additionally, the list of enum constants must be terminated by a semicolon;
 *
 * Type-Safe:
 * Enum is type-safe, you can not assign anything else other than predefined Enum constants to an Enum variable
 *
 * Enum Constants:
 * Enum constants are implicitly static and final and can not be changed once created
 *
 *
 * In Java Enum can override methods also
 *
 * You can define abstract methods inside Enum in Java *
 *
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("Following is the Time Table\n");
        for (TimeTable day: TimeTable.values()){
            System.out.println(day.getTimeTable());
        }
    }
}
