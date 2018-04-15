package CP125;

import java.util.Objects;

/**
 * @author jcrowley
 */

public class Person {
    private String firstName;
    private String lastName;
    public String addr;
    public String city;
    public String state;
    public String zip;

    public static void main(String[] args) {

    }

    @Override
    public boolean equals(Object obj) {
        boolean result = false;

        if (obj == null){
            result = false;
        }
        else if (obj == this){
            result = true;
        }
        else if (!(obj instanceof Person)){
            result = false;
        }
        else {
            Person that = (Person)obj;
//            if (this.firstName != null && that.firstName == null){
//                result = false;
//            }
//            else if (!this.firstName.equals(that.firstName)){
//                result = false;
//            }
//            else if (this.lastName != null && that.lastName == null){
//                result = false;
//            }
//            else{
//                result = lastName.equals(that.lastName);
//            }
            result = Objects.equals(this.firstName, that.firstName) &&
                     Objects.equals(this.lastName,that.lastName);
        }
        return result;
    }

    private boolean objectEquals(Object obj1, Object obj2){
        if (obj1 == obj2)
            return true;
        else if (obj1 == null)
            return false;
        else
            return obj1.equals(obj2);
    }

    public int hashCode(){
        Object addr;
        Object city;
        Object state;
        Object zip;
        int hash = Objects.hash(addr,city,state,zip)
    }

    public int hashCode(){
        int hashCode = 0;
        int prime = 31;
        hashCode += addr.hashCode();
        hashCode += city.hashCode() * prime;
        hashCode += state.hashCode() * prime;
        hashCode += zip.hashCode() * prime;

    }
}
