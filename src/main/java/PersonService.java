import javax.xml.validation.Validator;
import java.util.List;

public class PersonService {
    public Person createPerson(String name, List<String> phoneNumbers, String postalCode, int age, String favoriteToy){
        if(age < 14){
            return createKid(name, phoneNumbers, postalCode, age, favoriteToy);
        }else{
            return createAdult(name, phoneNumbers, postalCode, favoriteToy);
        }
    }

    private Person createKid(String name, List<String> phoneNumbers, String postalCode, int age, String favoriteToy){
        PersonValidator validator = new PersonValidator();
        Kid kid = new Kid();
        kid.favoriteToy = favoriteToy;

        kid.phoneNumbers = phoneNumbers;
        kid.name = name;
        kid.postalCode = postalCode;

        try{
            List<String> result = validator.validate(kid);

            if(result.size() > 0){
                System.out.println("Errors in person validation");

                for(String error : result){
                    System.out.println(error);
                }
                return null;
            }
        } catch(InvalidPersonException invalidPersonException){
            System.out.println(invalidPersonException.getMessage());
            return null;
        }

        try {
            storePerson(kid);
        } catch(Exception exception){
            System.out.println(exception);
        }
        return kid;
    }

    private Person createAdult(String name, List<String> phoneNumbers, String postalCode, String favoriteToy){
        PersonValidator validator = new PersonValidator();
        Adult adult = new Adult();
        adult.phoneNumbers = phoneNumbers;
        adult.name = name;
        adult.postalCode = postalCode;

        try{
            List<String> result = validator.validate(adult);

            if(result.size() > 0){
                System.out.println("Errors in person validation");

                for(String error : result){
                    System.out.println(error);
                }
                return null;
            }
        } catch(InvalidPersonException invalidPersonException){
            System.out.println(invalidPersonException.getMessage());
            return null;
        }

        try {
            storePerson(adult);
        } catch(Exception exception){
            System.out.println(exception);
        }
        return adult;
    }

    private void storePerson(Person person) throws Exception {
        if(person == null){
            throw new Exception();
        }
    }
}