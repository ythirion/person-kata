import javax.xml.validation.Validator;
import java.util.List;

public class PersonService {
    public Person createPerson(String name, List<String> phoneNumbers, String postalCode, int age, String favoriteToy){
        PersonValidator validator = new PersonValidator();
        Person newPerson = null;

        if(age < 14){
            newPerson = new Kid();
            ((Kid) newPerson).favoriteToy = favoriteToy;
        }else{
            newPerson = new Adult();
        }

        newPerson.phoneNumbers = phoneNumbers;
        newPerson.name = name;
        newPerson.postalCode = postalCode;

        try{
            List<String> result = validator.validate(newPerson);

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
            storePerson(newPerson);
        } catch(Exception exception){
            System.out.println(exception);
        }

        return newPerson;
    }

    private void storePerson(Person person) throws Exception {
        if(person == null){
            throw new Exception();
        }
    }
}