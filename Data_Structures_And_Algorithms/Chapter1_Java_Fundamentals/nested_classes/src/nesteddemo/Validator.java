package nesteddemo;

/**
 * @author Hikamt Dhamee
 * @email me.hemant.available@gmail.com
 */
public class Validator {
    private int a = 0;

    public Validator(){

    }

    public static class PhoneNumberValidator {
        private int a = 1; // 1 for phone number validator, just for demonstrating purpose
        private String phoneNumber = null;

        public PhoneNumberValidator(String phoneNumber){
            this.phoneNumber = phoneNumber;
        }

        public boolean validatePhoneNumber(){
            System.out.println("Executing phone number validator: " + a);
            if ( a == 1 && phoneNumber != null && phoneNumber.length() < 11){
                return true;
            }
            return false;
        }
    }

    public class ZipCodeValidator{
        private  int a = 2;
        private  String zipCode = null;

        public ZipCodeValidator(String zipCode) {
            this.zipCode = zipCode;
        }

        public boolean validateZipCode(){
            System.out.println("Executing zip code validator: " + a);
            if (a == 2 && zipCode != null && zipCode.length() > 3){
                return true;
            }
            return false;
        }
    }
}
