import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class PersonService {
    private final Logger logger;

    public Person createPerson(String name, String[] phoneNumbers,String postalCode, int age, String favoriteToy){
        PersonValidator validator = new PersonValidator();
        Person person = age < 14 ?
                new Kid(name, phoneNumbers, postalCode, favoriteToy) :
                new Adult(name, phoneNumbers, postalCode);

        try{
            List<String> result = validator.validate(person);

            if(result.size() > 0){
                logger.log("Errors in person validation");

                for(String error : result){
                    logger.log(error);
                }
                return null;
            }
        } catch(InvalidPersonException invalidPersonException){
            logger.log(invalidPersonException.getMessage());
            return null;
        }

        try {
            storePerson(person);
        } catch(Exception exception){
            logger.log(exception.getMessage());
        }
        return person;
    }

    private void storePerson(Person person) throws Exception {
        if(person == null){
            throw new Exception();
        }
    }
}